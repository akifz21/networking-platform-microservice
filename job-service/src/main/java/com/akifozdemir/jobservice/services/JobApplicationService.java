package com.akifozdemir.jobservice.services;

import com.akifozdemir.jobservice.dtos.JobApplicationRequest;
import com.akifozdemir.jobservice.dtos.JobApplicationResponse;
import com.akifozdemir.jobservice.mappers.JobApplicationMapper;
import com.akifozdemir.jobservice.models.JobApplication;
import com.akifozdemir.jobservice.repositories.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 JobApplicationMapper jobApplicationMapper ){
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    public void add(JobApplicationRequest jobApplicationRequest){
        JobApplication jobApplication =
                this.jobApplicationMapper.requestToJobApplication(jobApplicationRequest);
        this.jobApplicationRepository.save(jobApplication);
    }

    public void delete(UUID id){
        this.jobApplicationRepository.deleteById(id);
    }

    public JobApplicationResponse getById(UUID id){
        JobApplication jobApplication =
                this.jobApplicationRepository.findById(id)
                        .orElseThrow(()->new RuntimeException("Job Application not found."));
        return this.jobApplicationMapper.jobApplicationToResponse(jobApplication);
    }

    public List<JobApplicationResponse> getByJob(UUID jobId){
        List<JobApplication> jobApplications =
                this.jobApplicationRepository.findByJob_Id(jobId);
        return jobApplications
                .stream()
                .map(jobApplication -> this.jobApplicationMapper.jobApplicationToResponse(jobApplication))
                .collect(Collectors.toList());
    }

    public List<JobApplicationResponse> getByUser(UUID userId){
        List<JobApplication> jobApplications =
                this.jobApplicationRepository.findByUserId(userId);
        return jobApplications
                .stream()
                .map(jobApplication -> this.jobApplicationMapper.jobApplicationToResponse(jobApplication))
                .collect(Collectors.toList());

    }

}
