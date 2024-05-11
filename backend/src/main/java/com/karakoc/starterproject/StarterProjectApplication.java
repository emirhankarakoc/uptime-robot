package com.karakoc.starterproject;

import com.karakoc.starterproject.project.ProjectRepository;
import com.karakoc.starterproject.security.JWTService;
import com.karakoc.starterproject.user.User;
import com.karakoc.starterproject.user.UserRepository;
import com.karakoc.starterproject.user.UserType;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class StarterProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterProjectApplication.class, args);
    }

    @Component
    @AllArgsConstructor
    public class MyCommandLineRunner implements CommandLineRunner {
        private final UserRepository repository;
        private final JWTService jwtService;


        @Override
        public void run(String... args) throws Exception {
            User admin = new User();
            admin.setId(UUID.randomUUID().toString());
            admin.setUsername("emirhan");
            admin.setPassword("emirhan");
            admin.setType(UserType.ADMIN);
            admin.setMail("emirhankarakoc@yahoo.com");
            admin.setCreatedAt(LocalDateTime.now());
            admin.setUpdatedAt(LocalDateTime.now());
            admin.setToken(jwtService.generateToken(admin.getUsername()));
            repository.save(admin);
            System.out.println(admin.getToken());

        }
    }

}
