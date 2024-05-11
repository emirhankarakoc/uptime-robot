package com.karakoc.starterproject.pdf;

import com.karakoc.starterproject.attempt.AttemptRepository;
import com.karakoc.starterproject.attempt.AttemptService;
import com.karakoc.starterproject.request.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PDFService {
    //pdf export edecek.
    private final AttemptService service;
}
