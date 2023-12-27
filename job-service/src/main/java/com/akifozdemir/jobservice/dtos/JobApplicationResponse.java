package com.akifozdemir.jobservice.dtos;

import com.akifozdemir.jobservice.models.Job;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public record JobApplicationResponse(
        UUID id,
        UUID userId,
        UUID jobId
) { }
