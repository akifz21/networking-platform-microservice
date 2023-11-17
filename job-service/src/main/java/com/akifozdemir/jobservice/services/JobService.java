package com.akifozdemir.jobservice.services;

import com.akifozdemir.jobservice.clients.CompanyClient;
import com.akifozdemir.jobservice.dtos.CompanyResponse;
import com.akifozdemir.jobservice.dtos.JobRequest;
import com.akifozdemir.jobservice.dtos.JobResponse;
import com.akifozdemir.jobservice.mappers.JobMapper;
import com.akifozdemir.jobservice.models.Job;
import com.akifozdemir.jobservice.repositories.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final CompanyClient companyClient;
    public JobService(JobRepository jobRepository, JobMapper jobMapper,
                      CompanyClient companyClient){
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
        this.companyClient = companyClient;
    }
    public void add(JobRequest jobRequest){
        Job job = this.jobMapper.requestToJob(jobRequest);
        this.jobRepository.save(job);
    }

    public List<JobResponse> getAll(){
            List<Job> jobs = this.jobRepository.findAll();
            return jobs.stream()
                    .map(job -> {
                        CompanyResponse companyResponse = this.companyClient.getCompany(job.getCompanyId());
                        return this.jobMapper.jobToResponse(job,companyResponse);
                    }).collect(Collectors.toList());
    }

    public JobResponse getById(UUID id){
        Job job = this.jobRepository.findById(id).get();
        CompanyResponse company = this.companyClient.getCompany(job.getCompanyId());
        return this.jobMapper.jobToResponse(job,company);
    }
}
