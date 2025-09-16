package com.example.CareerBridge.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.CareerBridge.model.Job;

public interface JobRepo extends MongoRepository<Job, String> {

    List<Job> findByProfileContainingIgnoreCase(String profile);

    List<Job> findByTechsContainingIgnoreCase(String tech);
    
    List<Job> findByExpGreaterThanEqual(int exp);

}

