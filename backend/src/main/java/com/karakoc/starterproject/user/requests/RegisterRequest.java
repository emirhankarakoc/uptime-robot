package com.karakoc.starterproject.user.requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
