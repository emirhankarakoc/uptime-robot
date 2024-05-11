package com.karakoc.starterproject.project;

import com.karakoc.starterproject.request.Request;

import java.util.List;

public interface ProjectService {
    Project createProject(CreateProjectRequest r);
    Project getProjectById(String id);

    List<Project> getAll();
}
