package com.karakoc.starterproject.attempt;

import com.karakoc.starterproject.request.Method;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
public class Attempt {
    @Id
    private String id;
    private String url;
    private LocalDateTime created;
    private String body;
    private String projectId;
    @ElementCollection
    private Map<String, String> headers;
    private Method httpMethod;
    private String response;
    private String responseCode;
}
