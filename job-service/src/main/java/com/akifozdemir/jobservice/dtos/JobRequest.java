package com.akifozdemir.jobservice.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record JobRequest (String title, String description, UUID companyId, LocalDateTime endDate){
}
