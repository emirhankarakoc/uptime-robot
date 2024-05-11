package com.karakoc.starterproject.request;

import com.karakoc.starterproject.request.requests.CreateHttpRequest;

public interface RequestService {

    void request(Request r);

    void requestAll();
    RequestDTO createRequest(CreateHttpRequest r);
    RequestDTO changeBody(String requestId,String body);
    RequestDTO getRequestById(String id);
    RequestDTO setRequestStatusLive(String downedRequestId);
    void deleteRequest(String requestId);
    RequestDTO setRequestStatusDown(String downedRequestId);
}
