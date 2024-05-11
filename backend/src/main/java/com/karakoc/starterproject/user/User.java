package com.karakoc.starterproject.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.karakoc.starterproject.project.Project;
import com.karakoc.starterproject.user.requests.RegisterRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String mail;
    @Enumerated
    private UserType type;


    @OneToMany
    @JoinColumn(name = "projectId")
    private List<Project> projects;

    private String token;//mistik.


    public static User createSimpleUser(RegisterRequest r) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(r.getUsername());
        // Şifreyi BCrypt ile hashle
        String hashedPassword = BCrypt.withDefaults().hashToString(12, r.getPassword().toCharArray());
        user.setPassword(hashedPassword);
        user.setMail("empty");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setType(UserType.USER);
        return user;
    }

    public static void updateUser(User u) {
        u.setUpdatedAt(LocalDateTime.now());
    }

    public static UserDTO userToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setMail(user.getMail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setType(user.getType());
        return dto;
    }
}
