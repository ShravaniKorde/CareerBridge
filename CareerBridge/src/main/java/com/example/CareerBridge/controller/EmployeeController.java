package com.example.CareerBridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CareerBridge.model.Job;
import com.example.CareerBridge.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final JobService jobService;

    @GetMapping("/jobs")
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();

    }

    @GetMapping("/jobs/profile/{profile}")
    public List<Job> getByProfile(@PathVariable String profile) {

        return jobService.searchByProfile(profile);

    }

    @GetMapping("/jobs/tech/{tech}")
    public List<Job> getByTech(@PathVariable String tech) {

        return jobService.searchByTech(tech);

    }

    @GetMapping("/jobs/exp/{exp}")
    public List<Job> getByExperience(@PathVariable int exp) {

        return jobService.searchByExperience(exp);
        
    }
}

