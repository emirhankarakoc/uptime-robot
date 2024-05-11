package com.karakoc.starterproject.attempt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt,String> {

    List<Attempt> findAllByCreatedIsBefore(LocalDateTime time);
}
