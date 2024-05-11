package com.karakoc.starterproject.config.security;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecurityConfig {
    private final String secretKey = "111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc111222333abc";
    private final long expirationDate = System.currentTimeMillis() + 1000 * 60 * 15; //15 minutes
}
