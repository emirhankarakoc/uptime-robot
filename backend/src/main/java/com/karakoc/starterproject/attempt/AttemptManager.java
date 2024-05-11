package com.karakoc.starterproject.attempt;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
 @AllArgsConstructor
public class AttemptManager implements AttemptService{
    private final AttemptRepository repository;



    public List<Attempt> getAllAttempts(){
        return repository.findAll();
    }

    public List<Attempt> getAllAttemptsDaily(){
        return repository.findAllByCreatedIsBefore(LocalDateTime.now().plusDays(1));
    }
}

