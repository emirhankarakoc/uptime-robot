package com.karakoc.starterproject.attempt;

import java.util.List;

public interface AttemptService {
    List<Attempt> getAllAttempts();
    List<Attempt> getAllAttemptsDaily();
}
