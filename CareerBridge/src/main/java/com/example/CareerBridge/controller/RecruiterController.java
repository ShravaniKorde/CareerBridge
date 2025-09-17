package com.example.CareerBridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CareerBridge.model.Job;
import com.example.CareerBridge.model.JobRequestDTO;
import com.example.CareerBridge.model.JobResponseDTO;
import com.example.CareerBridge.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recruiter")
@RequiredArgsConstructor
public class RecruiterController {

    private final JobService jobService;

     @GetMapping("/getAllJobs")
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/jobById/{id}")
    public JobResponseDTO getJobById(@PathVariable String id) {
        return jobService.getJobById(id);
    }

    @PostMapping("/postJob")
    public JobResponseDTO postJob(@RequestBody JobRequestDTO jobDto) {
        return jobService.addJob(jobDto);
    }

    @PutMapping("/updateJobById/{id}")
    public JobResponseDTO updateJob(@PathVariable String id, @RequestBody JobRequestDTO jobDto) {
        return jobService.updateJobById(id, jobDto);
    }

    @DeleteMapping("/deleteJobById/{id}")
    public String deleteJob(@PathVariable String id) {
        jobService.deleteJobById(id);
        return "Job with id " + id + " deleted successfully";
    }

}
