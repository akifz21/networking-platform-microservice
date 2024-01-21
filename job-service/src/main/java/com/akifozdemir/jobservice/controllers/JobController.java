package com.akifozdemir.jobservice.controllers;

import com.akifozdemir.jobservice.dtos.JobRequest;
import com.akifozdemir.jobservice.dtos.JobResponse;
import com.akifozdemir.jobservice.services.JobService;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class JobController {

    private final JobService jobService;
    public JobController(JobService jobService){
        this.jobService = jobService;
    }
    @GetMapping
    public ResponseEntity<List<JobResponse>> getAll(){
        return ResponseEntity.ok().body(this.jobService.getAll());
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getByCompany(@PathVariable UUID companyId){
        return ResponseEntity.ok().body(this.jobService.getByCompany(companyId));
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody JobRequest jobRequest){
        this.jobService.add(jobRequest);
        return ResponseEntity.ok().body("Successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(this.jobService.getById(id));
    }


}
