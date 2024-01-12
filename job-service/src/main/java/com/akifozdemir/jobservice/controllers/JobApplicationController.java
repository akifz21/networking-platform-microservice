package com.akifozdemir.jobservice.controllers;

import com.akifozdemir.jobservice.JobServiceApplication;
import com.akifozdemir.jobservice.dtos.JobApplicationRequest;
import com.akifozdemir.jobservice.dtos.JobApplicationResponse;
import com.akifozdemir.jobservice.models.JobApplication;
import com.akifozdemir.jobservice.services.JobApplicationService;
import feign.Response;
import jakarta.persistence.PostRemove;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    public JobApplicationController(JobApplicationService jobApplicationService){
        this.jobApplicationService = jobApplicationService;

    }
    @PostMapping
    public ResponseEntity<String> apply(JobApplicationRequest jobApplicationRequest){
        this.jobApplicationService.add(jobApplicationRequest);
        return ResponseEntity.ok().body("Applied Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(UUID id){
        this.jobApplicationService.delete(id);
        return ResponseEntity.ok().body("Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getById(UUID id){
        return ResponseEntity.ok().body(this.jobApplicationService.getById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<JobApplicationResponse>> getByUser(UUID id){
        return ResponseEntity.ok().body(this.jobApplicationService.getByUser(id));
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<List<JobApplicationResponse>> getByJob(UUID id){
        return ResponseEntity.ok().body(this.jobApplicationService.getByJob(id));
    }


}
