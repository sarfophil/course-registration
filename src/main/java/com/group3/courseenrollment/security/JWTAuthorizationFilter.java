package com.group3.courseenrollment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.group3.courseenrollment.configuration.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * @author SARFO PHILIP
 * {@link JWTAuthenticationFilter} Filter extends Spring Security's filter and
 * changes the behaviour by adding JWT authorization before client can consume
 * resource server
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private ApplicationProperties applicationProperties;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,ApplicationProperties applicationProperties) {
        super(authenticationManager);
        this.applicationProperties = applicationProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Gets the header from the request
        String header = request.getHeader(applicationProperties.getJwt().getHeaderString());

        // Validates header
        if (header == null || !header.startsWith(applicationProperties.getJwt().getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        // Verifies token again
        Optional<UsernamePasswordAuthenticationToken> authentication
                = getAuthentication(request);

        authentication.ifPresentOrElse(auth-> {
            // Sets the current verified token to the SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(auth);
        },()->{
            log.info("Authentication not found");
        });



        // Continue ...
        chain.doFilter(request, response);
    }

    /**
     * getAuthentication verifies user's Jwt token
     * @param request
     * @return
     */
    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(HttpServletRequest request){
        String token = request.getHeader(applicationProperties.getJwt().getHeaderString());
        // Token is checked
        if(token != null){
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(applicationProperties.getJwt().getSecret().getBytes()))
                    .build()
                    .verify(token.replace(applicationProperties.getJwt().getTokenPrefix(), ""))
                    .getSubject();

            if (user != null){
                return Optional.of(new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>()));
            }
            return Optional.empty();

        }
        return Optional.empty();
    }
}
