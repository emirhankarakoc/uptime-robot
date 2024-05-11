package com.karakoc.starterproject.request;


import com.karakoc.starterproject.attempt.Attempt;
import com.karakoc.starterproject.attempt.AttemptRepository;
import com.karakoc.starterproject.config.mailsender.MailSenderService;
import com.karakoc.starterproject.exceptions.general.BadRequestException;
import com.karakoc.starterproject.exceptions.general.NotfoundException;
import com.karakoc.starterproject.project.Project;
import com.karakoc.starterproject.project.ProjectRepository;
import com.karakoc.starterproject.project.ProjectService;
import com.karakoc.starterproject.request.requests.CreateHttpRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.karakoc.starterproject.request.Request.requestToDTO;


@Service
@Slf4j
@AllArgsConstructor
public class RequestManager implements RequestService{
    private final RequestRepository repository;
    private final AttemptRepository attemptRepository;
    private final MailSenderService mailService;
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @Transactional
    public void request(Request r) {
        RestTemplate restTemplate = new RestTemplate();
        Attempt request = new Attempt();
        try {
            String url = r.getUrl();
            request.setId(UUID.randomUUID().toString());
            request.setUrl(url);
            String requestBody = r.getBody();
            request.setBody(requestBody);
            HttpHeaders headers = new HttpHeaders();
                for (Map.Entry<String, String> entry : r.getHeaders().entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
            request.setHeaders(r.getHeaders());
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = null;
            request.setHttpMethod(r.getHttpMethod());
            switch (r.getHttpMethod()) {
                case GET -> {
                    response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
                }
                case POST -> {
                    response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
                }
                case PUT -> {
                    response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
                }
                case DELETE -> {
                    response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
                    break;
                }
            }
            request.setCreated(LocalDateTime.now());
            request.setResponse(response.getBody());
            request.setResponseCode(response.getStatusCode().toString());
            request.setProjectId(r.getProjectId());
            attemptRepository.save(request);
            log.info("bir gariban eklendi ve su siteye istek atildi." + request.getUrl());
        }
        catch (Exception e){
            request.setProjectId(r.getProjectId());
            request.setResponse(e.getMessage().substring(5));//error description
            request.setResponseCode(e.getMessage().substring(0,4));//error code
            attemptRepository.save(request);
            Project project = projectService.getProjectById(request.getProjectId()) ;
            mailService.errorRequest(project.getUserMail());
            r.setStatus(Status.DOWN);
            repository.save(r);
            log.info("birinin itegi basarisiz oldu. deneme kaydedildi ve istek listesinden cikartildi.");
        }

    }
    @Transactional
    public void requestAll() {
        List<Request> allRequests = repository.findAllByStatus(Status.LIVE);
        for (Request r:allRequests) {
            request(r);
        }
    }


    public RequestDTO createRequest(CreateHttpRequest r){
        Project project = projectService.getProjectById(r.getProjectId());
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setUrl(r.getUrl());
        request.setBody(r.getBody());
        request.setCreated(LocalDateTime.now());
        request.setProjectId(r.getProjectId());
        request.setHttpMethod(r.getHttpMethod());
        request.setHeaders(r.getHeaders());
        request.setStatus(Status.LIVE);
        repository.save(request);
        project.getRequests().add(request);
        projectRepository.save(project);
        return requestToDTO(request);
    }

    public RequestDTO getRequestById(String id){
        Request request = repository.findById(id).orElseThrow(()-> new NotfoundException("Request not found."));
        return requestToDTO(request);
    }

    public RequestDTO changeBody(String requestId,String body){
        Request request = getRequest(requestId);
        request.setBody(body);
        repository.save(request);
        return requestToDTO(request);
    }

    public RequestDTO setRequestStatusLive(String downedRequestId){
        Request request = getRequest(downedRequestId);
        if (request.getStatus().equals(Status.LIVE)){throw new BadRequestException("This request already live, stupid.");
        }
        request.setStatus(Status.LIVE);
        repository.save(request);
        return requestToDTO(request);
    }
    public RequestDTO setRequestStatusDown(String downedRequestId){
        Request request = getRequest(downedRequestId);
        if (request.getStatus().equals(Status.DOWN)){throw new BadRequestException("This request already live, stupid.");
        }
        request.setStatus(Status.DOWN);
        repository.save(request);
        return requestToDTO(request);
        //why?
    }

    public void deleteRequest(String requestId){
        Request request = getRequest(requestId);
        repository.delete(request);
    }



    private Request getRequest(String id){
        Request request = repository.findById(id).orElseThrow(()-> new NotfoundException("Request not found."));

        return request;
    }



}
