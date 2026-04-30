package com.abbtech.dto.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private String[] publicUrls;
    private Jwt jwt = new Jwt();

    public static class Jwt {
        private String secret;
        private long accessExp;
        private long refreshExp;

        // Getters and Setters
        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public long getAccessExp() {
            return accessExp;
        }

        public void setAccessExp(long accessExp) {
            this.accessExp = accessExp;
        }

        public long getRefreshExp() {
            return refreshExp;
        }

        public void setRefreshExp(long refreshExp) {
            this.refreshExp = refreshExp;
        }
    }

    // Getters and Setters for top level
    public String[] getPublicUrls() {
        return publicUrls;
    }

    public void setPublicUrls(String[] publicUrls) {
        this.publicUrls = publicUrls;
    }

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }
}

