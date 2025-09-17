package com.example.CareerBridge.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.CareerBridge.model.Job;

public interface JobRepo extends MongoRepository<Job, String> {

 // Search by profile (case insensitive)
    @Query("{ 'profile': { $regex: ?0, $options: 'i' } }")
    List<Job> findByProfile(String profile);

    // Find jobs requiring <= given experience
    @Query("{ 'exp': { $lte: ?0 } }")
    List<Job> findByMaxExperience(int exp);

    // Search by tech
    @Query("{ 'techs': { $in: ?0 } }")
    List<Job> findByTechsIn(List<String> techs);

}

