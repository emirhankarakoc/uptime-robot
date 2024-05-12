package com.karakoc.starterproject.project.requests;

import lombok.Data;

@Data
public class GetProjectByIdRequest {
private String token;
private String projectId;
}
