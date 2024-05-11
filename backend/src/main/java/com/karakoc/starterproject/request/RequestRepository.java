package com.karakoc.starterproject.request;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,String>{
    List<Request> findAllByStatus(Status status);
}
