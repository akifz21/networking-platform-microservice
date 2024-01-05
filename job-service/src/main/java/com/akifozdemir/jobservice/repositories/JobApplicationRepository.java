package com.akifozdemir.jobservice.repositories;

import com.akifozdemir.jobservice.models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID> {
    List<JobApplication> findByJob_Id(UUID jobId);
    List<JobApplication> findByUserId(UUID userId);

}
