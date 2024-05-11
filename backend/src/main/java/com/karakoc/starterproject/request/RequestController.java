package com.karakoc.starterproject.request;


import com.karakoc.starterproject.request.requests.CreateHttpRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/requests")
@Tag(name = "Requests Controller")
public class RequestController {
    private final RequestService service;


    @PostMapping
    public RequestDTO createRequest(@RequestBody CreateHttpRequest r){
        return service.createRequest(r);
    }


    @PutMapping("/body")
    public RequestDTO changeBody(@RequestBody String requestId,@RequestBody String body){
        return service.changeBody(requestId,body);
    }
    @PutMapping("/status/live")
    public RequestDTO setRequestStatusLive(@RequestBody String downedRequestId){
        return service.setRequestStatusLive(downedRequestId);
    }

    @GetMapping("/{id}")
    public RequestDTO getRequestById(@PathVariable String id){
        return service.getRequestById(id);
    }

}
