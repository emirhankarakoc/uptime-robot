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
    private Map<String, String> headers;
    private String response;
    private String responseCode;
    private Method httpMethod;
}
