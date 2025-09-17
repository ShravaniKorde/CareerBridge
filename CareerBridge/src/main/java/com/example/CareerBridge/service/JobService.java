package com.example.CareerBridge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.CareerBridge.model.Job;
import com.example.CareerBridge.model.JobRequestDTO;
import com.example.CareerBridge.model.JobResponseDTO;
import com.example.CareerBridge.repo.JobRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepo jobRepo;

     // Convert DTO -> Entity
    private Job toEntity(JobRequestDTO dto) {
        Job job = new Job();
        job.setProfile(dto.getProfile());
        job.setDesc(dto.getDesc());
        job.setExp(dto.getExp());
        job.setTechs(dto.getTechs());
        return job;
    }

    // Convert Entity -> DTO
    private JobResponseDTO toResponse(Job job) {
    return new JobResponseDTO(
    job.getId(),
    job.getProfile(),
    job.getExp(),    
    job.getDesc(),   
    job.getTechs()
);
    }

    // Get all jobs
    public List<JobResponseDTO> getAllJobs() {
        return jobRepo.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Get job by ID
    public JobResponseDTO getJobById(String id) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        return toResponse(job);
    }

    // Add job
    public JobResponseDTO addJob(JobRequestDTO dto) {
        Job job = toEntity(dto);
        Job saved = jobRepo.save(job);
        return toResponse(saved);
    }

    // Update job
    public JobResponseDTO updateJobById(String id, JobRequestDTO dto) {
        return jobRepo.findById(id)
                .map(job -> {
                    job.setProfile(dto.getProfile());
                    job.setDesc(dto.getDesc());
                    job.setExp(dto.getExp());
                    job.setTechs(dto.getTechs());
                    return toResponse(jobRepo.save(job));
                })
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    // Delete job
    public void deleteJobById(String id) {
        if (!jobRepo.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        jobRepo.deleteById(id);
    }

    // Search by profile
    public List<JobResponseDTO> searchByProfile(String profile) {
        return jobRepo.findByProfile(profile)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Filter by experience
    public List<JobResponseDTO> searchByExperience(int exp) {
        return jobRepo.findByMaxExperience(exp)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Filter by tech stack
    public List<JobResponseDTO> searchByTech(String tech) {
        return jobRepo.findByTechsIn(List.of(tech))
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

}

