package com.group3.courseenrollment.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties("com.group3.courseenrollment")
public class ApplicationProperties {

    private JWTProperties jwt = new JWTProperties();
    private Integer enrollmentLimitPerStudent;

    /**
     * Jwt Properties
     */
    @Data
    public static class JWTProperties{
        private String issuer;
        private String secret;
        private Long expirationTime;
        private String tokenPrefix;
        private String headerString;
    }
}
