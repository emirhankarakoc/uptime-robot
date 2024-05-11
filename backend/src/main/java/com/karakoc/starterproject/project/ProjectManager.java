package com.karakoc.starterproject.project;

import com.karakoc.starterproject.exceptions.general.BadRequestException;
import com.karakoc.starterproject.request.Request;
import com.karakoc.starterproject.user.User;
import com.karakoc.starterproject.user.UserDTO;
import com.karakoc.starterproject.user.UserRepository;
import com.karakoc.starterproject.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;


@Service
@AllArgsConstructor
public class ProjectManager implements ProjectService{
    private final ProjectRepository repository;
    private final UserService userService;
    private final UserRepository userRepository;

    public Project createProject(CreateProjectRequest r){
        User user = userService.getUserByToken(r.getToken());
        if (user.getMail().equals("empty")){
            throw new BadRequestException("You must enter your e-mail adress.");
        }


        Project project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setUserId(user.getId());

        repository.save(project);
        user.getProjects().add(project);
        userRepository.save(user);
        return project;
    }
}
