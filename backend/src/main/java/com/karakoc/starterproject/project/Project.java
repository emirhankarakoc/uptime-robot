package com.karakoc.starterproject.project;


import com.karakoc.starterproject.request.Request;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Project {

    @Id
    private String id;

    private String userId;
    @OneToMany
    @JoinColumn(name = "requestId")
    private List<Request> requests;

    private String schedule;




}
