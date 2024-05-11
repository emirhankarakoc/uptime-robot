package com.karakoc.starterproject.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private String id;
    private String username;
    private String mail;
    private String phoneNumber;
    private String firstname;
    private String lastname;
    private LocalDateTime birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserType type;
}
