package com.karakoc.starterproject.project;


import com.karakoc.starterproject.request.Request;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    private String id;
    private String projectName;
    private String userId;
    private String userMail;
    private List<Request> requests;
}
