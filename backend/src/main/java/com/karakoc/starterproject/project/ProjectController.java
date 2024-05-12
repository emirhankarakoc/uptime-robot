package com.karakoc.starterproject.project;


import com.karakoc.starterproject.project.requests.CreateProjectRequest;
import com.karakoc.starterproject.project.requests.GetProjectByIdRequest;
import com.karakoc.starterproject.security.JWTService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/projects")
@RestController
@AllArgsConstructor
@Tag(name = "Projects Controller")

public class ProjectController {
    private final ProjectService service;
    private final JWTService jwtService;



    @PostMapping
    public ProjectDTO postProject (@RequestBody CreateProjectRequest r)

    {
        jwtService.isLoddedIn(r.getToken());
        return  service.createProject(r);
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@RequestBody GetProjectByIdRequest r){
            jwtService.isLoddedIn(r.getToken());
        return  service.getProjectById(r.getProjectId());
    }

    @GetMapping("/all/{token}")
    public List<ProjectDTO> getAll(@PathVariable String token){
        jwtService.onlyAdmin(token);
        return service.getAll();
    }
}
