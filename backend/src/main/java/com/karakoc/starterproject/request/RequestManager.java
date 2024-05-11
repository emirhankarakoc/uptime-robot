package com.karakoc.starterproject.request;


import com.karakoc.starterproject.attempt.Attempt;
import com.karakoc.starterproject.attempt.AttemptRepository;
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

    @Transactional
    public void request(Request r) {
        RestTemplate restTemplate = new RestTemplate();
        Attempt request = new Attempt();
        try {
            // Google'a gönderilecek isteğin URL'i
            String url = r.getUrl();
            request.setId(UUID.randomUUID().toString());
            request.setUrl(url);
            // Boş JSON gövde
            String requestBody = r.getBody();
            request.setBody(requestBody);
            // Header oluştur ve Authorization başlığını ekle
            HttpHeaders headers = new HttpHeaders();
                for (Map.Entry<String, String> entry : r.getHeaders().entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
            request.setHeaders(r.getHeaders());


            // Header'ları ve boş gövdeyi içeren bir HttpEntity oluştur
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = null;
            request.setHttpMethod(r.getHttpMethod());

            // İstek gönder ve cevabı al
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
        }
        catch (Exception e){
            request.setProjectId(r.getProjectId());

            request.setResponse(e.getMessage().substring(5));
            request.setResponseCode(e.getMessage().substring(0,4));
            attemptRepository.save(request);
        }

        // Cevabı yazdır
        log.info("bir gariban eklendi ve su siteye istek atildi." + request.getUrl());
    }
    @Transactional
    public void requestAll() {
        List<Request> allRequests = repository.findAll();
        for (Request r:allRequests) {
            request(r);
        }
    }


    public RequestDTO createRequest(CreateHttpRequest r){
        Request request = new Request();
        request.setId(UUID.randomUUID().toString());
        request.setUrl(r.getUrl());
        request.setBody(r.getBody());
        request.setCreated(LocalDateTime.now());
        request.setProjectId(r.getProjectId());
        request.setHttpMethod(r.getHttpMethod());
        request.setHeaders(r.getHeaders());
        repository.save(request);
        return requestToDTO(request);
    }
}
