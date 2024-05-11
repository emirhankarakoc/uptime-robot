package com.karakoc.starterproject.request;

import lombok.Data;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

@Data
public class RequestDTO {
    private String id;
    private String url;
    private String body;
    private String projectId;
    private String title;
    private Map<String, String> headers;
    private Method httpMethod;
    private Status status;
}
