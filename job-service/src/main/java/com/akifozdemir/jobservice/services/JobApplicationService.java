package com.akifozdemir.jobservice.services;

import com.akifozdemir.jobservice.clients.UserClient;
import com.akifozdemir.jobservice.dtos.JobApplicationRequest;
import com.akifozdemir.jobservice.dtos.JobApplicationResponse;
import com.akifozdemir.jobservice.dtos.UserResponse;
import com.akifozdemir.jobservice.mappers.JobApplicationMapper;
import com.akifozdemir.jobservice.models.JobApplication;
import com.akifozdemir.jobservice.repositories.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;
    private final UserClient userClient;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository,
                                 JobApplicationMapper jobApplicationMapper,
                                 UserClient userClient
                                 ){
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobApplicationMapper = jobApplicationMapper;
        this.userClient = userClient;
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
        UserResponse userResponse = this.userClient.getUserById(jobApplication.getUserId());
        return this.jobApplicationMapper.jobApplicationToResponse(jobApplication,userResponse);
    }

    public List<JobApplicationResponse> getByJob(UUID jobId){
        List<JobApplication> jobApplications =
                this.jobApplicationRepository.findByJob_Id(jobId);
        List<UUID> userIds = jobApplications
                .stream()
                .map(JobApplication::getUserId)
                .distinct()
                .collect(Collectors.toList());
        Map<UUID,UserResponse> userMap = userClient.getUsersByIds(userIds)
                .stream()
                .collect(Collectors.toMap(UserResponse::getId, Function.identity()));
        return jobApplications.stream()
                .map(jobApplication -> {
                    UserResponse userResponse = userMap.get(jobApplication.getUserId());
                    return this.jobApplicationMapper.jobApplicationToResponse(jobApplication,userResponse);
                }).collect(Collectors.toList());
    }

    public List<JobApplicationResponse> getByUser(UUID userId){
       try {
           UserResponse userResponse =
                   this.userClient.getUserById(userId);
           return this.jobApplicationRepository.findByUserId(userResponse.getId())
                   .stream()
                   .map(jobApplication -> this.jobApplicationMapper.jobApplicationToResponse(jobApplication,userResponse))
                   .collect(Collectors.toList());
       }catch (Exception e){
            throw new RuntimeException(e);
       }

    }

}
