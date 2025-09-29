package com.example.CareerBridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CareerBridge.model.Job;
import com.example.CareerBridge.model.JobResponseDTO;
import com.example.CareerBridge.service.JobService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final JobService jobService;

    @GetMapping("/getAllJobs")
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/jobs/profile/{profile}")
    public List<JobResponseDTO> getByProfile(@PathVariable String profile) {
        return jobService.searchByProfile(profile);
    }

    @GetMapping("/jobs/tech/{tech}")
    public List<JobResponseDTO> getByTech(@PathVariable String tech) {
        return jobService.searchByTech(tech);
    }

    @GetMapping("/jobs/exp/{exp}")
    public List<JobResponseDTO> getByExperience(@PathVariable int exp) {
        return jobService.searchByExperience(exp);
    }
}

