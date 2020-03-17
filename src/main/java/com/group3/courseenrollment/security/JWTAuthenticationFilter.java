package com.group3.courseenrollment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.group3.courseenrollment.configuration.ApplicationProperties;
import com.group3.courseenrollment.domain.Credential;
import com.group3.courseenrollment.dto.JwtTokenDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author SARFO PHILIP
 * {@link JWTAuthenticationFilter}.Filter basically does the authentication and generation of JWT tokens. It customizes
 * the {@link UsernamePasswordAuthenticationFilter} behaviour.
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private ApplicationProperties applicationProperties;




    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,ApplicationProperties applicationProperties) {
        this.authenticationManager = authenticationManager;
        this.applicationProperties = applicationProperties;

    }




    /**
     * Authentication of user email and password process in this method
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Transactional
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            // Reading user email,password and mapping it to the Credential object
            Credential credential = new ObjectMapper().readValue(request.getInputStream(),Credential.class);


            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credential.getUsername(),
                            credential.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
           throw new RuntimeException();
        }


    }


    /**
     * When a user is successfully verified, It creates the token for the user
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Creation of Jwt Toke
        String token = JWT.create()
                        .withSubject(((UserDetails)authResult.getPrincipal()).getUsername()) // JWT Subject
                        .withExpiresAt(new Date(System.currentTimeMillis() + applicationProperties.getJwt().getExpirationTime()))// Lifetime of the token
                        .withIssuer(applicationProperties.getJwt().getIssuer())
                        .withIssuedAt(new Date())
                        .sign(Algorithm.HMAC512(applicationProperties.getJwt().getSecret().getBytes())); // Sign with the secret key

        // Properties to be added to the responses' header
        String headerString = applicationProperties.getJwt().getHeaderString();
        String tokenPrefix = applicationProperties.getJwt().getTokenPrefix();

        // Return the Jwt response to the client
        response.addHeader(headerString, tokenPrefix+ token);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(new Gson().toJson(new JwtTokenDto(token)));
    }
}
