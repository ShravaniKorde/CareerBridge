package com.example.CareerBridge.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CareerBridge.model.Job;
import com.example.CareerBridge.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recruiter")
@RequiredArgsConstructor
public class RecruiterController {

    private final JobService jobService;

    @PostMapping("/job")
    public Job postJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    // Update job by id
    @PutMapping("/job/{id}")
    public Job updateJob(@PathVariable String id, @RequestBody Job job) {
        return jobService.updateJobById(id, job);
    }

    // Delete job by id
    @DeleteMapping("/job/{id}")
    public String deleteJob(@PathVariable String id) {
        jobService.deleteJobById(id);
        return "Job with id " + id + " deleted successfully";
    }

}
