package com.karakoc.starterproject.project;


import com.karakoc.starterproject.request.Request;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {

    @Id
    private String id;
    private String projectName;
    private String userId;
    private String userMail;
    @OneToMany
    @JoinColumn(name = "requestId")
    private List<Request> requests;

    //private String schedule;

    public static ProjectDTO projectToDTO(Project project){
        ProjectDTO dto = new ProjectDTO();

        dto.setId(project.getId());
        dto.setProjectName(project.getProjectName());
        dto.setUserId(project.getUserId());
        dto.setUserMail(project.getUserMail());
        dto.setRequests(project.getRequests());
            return dto;
    }

    public static List<ProjectDTO> projectsToDTOS(List<Project> projectList){
        List<ProjectDTO> dtoList = new ArrayList<>();
        for(Project project : projectList){
            dtoList.add(projectToDTO(project));
        }
        return dtoList;
    }



}
