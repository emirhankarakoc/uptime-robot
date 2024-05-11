package com.karakoc.starterproject;

import com.karakoc.starterproject.project.Project;
import com.karakoc.starterproject.project.ProjectRepository;
import com.karakoc.starterproject.request.Method;
import com.karakoc.starterproject.request.Request;
import com.karakoc.starterproject.request.RequestRepository;
import com.karakoc.starterproject.request.Status;
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
import java.util.ArrayList;
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
        private final ProjectRepository projectRepository;
        private final RequestRepository requestRepository;

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
            admin.setToken("eyJhbGciOiJIUzI1NiJ9.eyJkZW5pemxpIjoiMjAyNCIsImVtaXJoYW4iOiJrYXJha29jIiwic3ViIjoiZW1pcmhhbiIsImlhdCI6MTcxNTQ1NzM0NiwiZXhwIjoxNzE1NDU4MjQ1fQ.VyDUEBOFcp5Q7RMhHJG8PihaIpofa88RD5lVks_BngI");
            repository.save(admin);

            System.out.println(admin.getToken());


            Project project = new Project();
            project.setId(UUID.randomUUID().toString());
            project.setUserId(admin.getId());
            project.setRequests(new ArrayList<>());
            project.setUserMail(admin.getMail());
            projectRepository.save(project);
            System.out.println(project.getId());

            Request request = new Request();
            request.setId(UUID.randomUUID().toString());
            request.setUrl("http://localhost:8080/users/test1");
            request.setHttpMethod(Method.GET);
            request.setCreated(LocalDateTime.now());
            request.setTitle("Localhost test1 get");
            request.setProjectId(project.getId());
            request.setStatus(Status.LIVE);
            requestRepository.save(request);
            project.getRequests().add(request);
            projectRepository.save(project);

            Request request2 = new Request();
            request2.setId(UUID.randomUUID().toString());
            request2.setUrl("http://localhost:8080/users/test0");
            request2.setHttpMethod(Method.GET);
            request2.setCreated(LocalDateTime.now());
            request2.setTitle("Localhost test1 get");
            request2.setProjectId(project.getId());
            request2.setStatus(Status.LIVE);
            requestRepository.save(request2);
            project.getRequests().add(request2);
            projectRepository.save(project);


        }
    }

}
