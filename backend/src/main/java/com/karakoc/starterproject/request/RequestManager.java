package com.karakoc.starterproject.request;


import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
@AllArgsConstructor
public class RequestManager implements RequestService{
    private final RequestRepository repository;
    public void request(Request r) {
        RestTemplate restTemplate = new RestTemplate();

        // Google'a gönderilecek isteğin URL'i
        String url = r.getUrl();

        // Boş JSON gövde
        String requestBody = r.getBody();

        // Header oluştur ve Authorization başlığını ekle
        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, String> entry : r.getHeaders().entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }



        // Header'ları ve boş gövdeyi içeren bir HttpEntity oluştur
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = null;
        // İstek gönder ve cevabı al
        switch (r.getHttpMethod()){
            case GET -> {response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            }
            
            case POST -> {response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            }
            case PUT -> {response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
            }
            case DELETE -> {response = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class); break;}
        }

        r.setResponse(response.getBody());
        r.setResponseCode(response.getStatusCode().toString());
        repository.save(r);

        // Cevabı yazdır
        

    }


}
