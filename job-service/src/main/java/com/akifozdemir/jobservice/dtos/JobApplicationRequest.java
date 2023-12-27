package com.akifozdemir.jobservice.dtos;

import java.util.UUID;

public record JobApplicationRequest(
        UUID id,
        UUID userId,
        UUID jobId
) {}
