package com.ssutopia.finacial.loanService.security;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JwtProperties {
    public static final String SECRET = "SomeSecretForJWTGeneration";
    public static final int EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static String getSECRET() {
        return SECRET;
    }

    public static int getExpirationTime() {
        return EXPIRATION_TIME;
    }

    public static String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

    public static String getHeaderString() {
        return HEADER_STRING;
    }
}

