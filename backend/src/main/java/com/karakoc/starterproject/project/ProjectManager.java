package com.karakoc.starterproject.project;

import com.karakoc.starterproject.exceptions.general.BadRequestException;
import com.karakoc.starterproject.exceptions.general.NotfoundException;
import com.karakoc.starterproject.project.requests.CreateProjectRequest;
import com.karakoc.starterproject.user.User;
import com.karakoc.starterproject.user.UserRepository;
import com.karakoc.starterproject.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.karakoc.starterproject.project.Project.projectToDTO;
import static com.karakoc.starterproject.project.Project.projectsToDTOS;


@Service
@AllArgsConstructor
public class ProjectManager implements ProjectService{
    private final ProjectRepository repository;
    private final UserService userService;
    private final UserRepository userRepository;

    public ProjectDTO createProject(CreateProjectRequest r){
        User user = userService.getUserByToken(r.getToken());
        if (user.getMail().equals("empty")){
            throw new BadRequestException("You must enter your e-mail adress.");
        }
        Project project = new Project();
        project.setId(UUID.randomUUID().toString());
        project.setUserId(user.getId());
        project.setUserMail(user.getMail());
        repository.save(project);
        user.getProjects().add(project);
        userRepository.save(user);
        return projectToDTO(project);
    }



    public List<ProjectDTO> getAll() {
        return projectsToDTOS(repository.findAll());
    }

    @Override
    public ProjectDTO getProjectById(String id) {
        Project project = getProject(id);
        return projectToDTO(project);
    }

    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
//PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
//PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
//PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS
    //PRIVATE HELPER METHODS

    private Project getProject(String id) {
        Project  project = repository.findById(id).orElseThrow(()->new NotfoundException("Project not found."));
        return project;
    }


}
