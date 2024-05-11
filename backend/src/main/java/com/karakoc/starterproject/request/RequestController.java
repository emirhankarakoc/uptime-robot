package com.karakoc.starterproject.request;


import com.karakoc.starterproject.request.requests.CreateHttpRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/requests")
public class RequestController {
    private final RequestService service;


    @PostMapping
    public RequestDTO createRequest(@RequestBody CreateHttpRequest r){
        return service.createRequest(r);
    }
}
