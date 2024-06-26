package com.karakoc.starterproject.request;


import com.karakoc.starterproject.request.requests.CreateHttpRequest;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class Request {

    @Id
    private String id;
    private String title;
    private String url;
    private LocalDateTime created;
    private String body;
    private String projectId;
    @ElementCollection
    private Map<String, String> headers;
    private Method httpMethod;
    private Status status;



    public static Request createSimpleRequest(CreateHttpRequest r){
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setUrl(r.getUrl());
        request.setBody(r.getBody());
        request.setHeaders(r.getHeaders());
        request.setHttpMethod(r.getHttpMethod());
        request.setTitle(r.getTitle());
        return request;
    }
    public static RequestDTO requestToDTO(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId());
        dto.setUrl(request.getUrl());
        dto.setBody(request.getBody());
        dto.setProjectId(request.getProjectId());
        dto.setHeaders(request.getHeaders());
        dto.setHttpMethod(request.getHttpMethod());
        dto.setStatus(request.getStatus());
        dto.setTitle(request.getTitle());
        return dto;
    }

}
