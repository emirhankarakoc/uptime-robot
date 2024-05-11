package com.karakoc.starterproject.request.requests;

import com.karakoc.starterproject.request.Method;
import jakarta.persistence.ElementCollection;
import lombok.Data;

import java.util.Map;

@Data
public class CreateHttpRequest {

    private String projectId;
    private String title;
    private String url;
    private String body;
    private Map<String, String> headers;
    private Method httpMethod;
    private String token;
}
