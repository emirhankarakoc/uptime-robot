package com.karakoc.starterproject.project;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/projects")
@RestController
@AllArgsConstructor
public class ProjectController {
    private final ProjectService service;



    @PostMapping
    public Project postProject (@RequestBody CreateProjectRequest r){
        return  service.createProject(r);
    }
}
