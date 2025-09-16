package com.example.CareerBridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CareerBridge.model.Job;
import com.example.CareerBridge.repo.JobRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepo jobRepo;

    public List<Job> getAllJobs() {
        return jobRepo.findAll();
    }

    public Job addJob(Job job) {
        return jobRepo.save(job);
    }

    public List<Job> searchByProfile(String profile) {
        return jobRepo.findByProfileContainingIgnoreCase(profile);
    }

    public List<Job> searchByTech(String tech) {
        return jobRepo.findByTechsContainingIgnoreCase(tech);
    }

    public List<Job> searchByExperience(int exp) {
        return jobRepo.findByExpGreaterThanEqual(exp);
    }

    public Job updateJobById(String id, Job updatedJob) {
    return jobRepo.findById(id)
            .map(job -> {
                job.setDesc(updatedJob.getDesc());
                job.setExp(updatedJob.getExp());
                job.setProfile(updatedJob.getProfile());
                job.setTechs(updatedJob.getTechs());
                return jobRepo.save(job);
            })
            .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
}

public void deleteJobById(String id) {
    if (!jobRepo.existsById(id)) {
        throw new RuntimeException("Job not found with id: " + id);
    }
    jobRepo.deleteById(id);
}

}

