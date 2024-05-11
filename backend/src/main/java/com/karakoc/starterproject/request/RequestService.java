package com.karakoc.starterproject.request;

import com.karakoc.starterproject.request.requests.CreateHttpRequest;

public interface RequestService {

    void request(Request r);

    void requestAll();
    RequestDTO createRequest(CreateHttpRequest r);
}
