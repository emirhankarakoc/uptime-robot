package com.karakoc.starterproject.project;

import com.karakoc.starterproject.project.requests.CreateProjectRequest;

import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(CreateProjectRequest r);
    ProjectDTO getProjectById(String id);

    List<ProjectDTO> getAll();
}
