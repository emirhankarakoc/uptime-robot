package com.karakoc.starterproject.project;


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



    @PostMapping
    public Project postProject (@RequestBody CreateProjectRequest r){
        return  service.createProject(r);
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable String id){
        return  service.getProjectById(id);
    }

    @GetMapping
    public List<Project> getAll(){
        return service.getAll();
    }
}
