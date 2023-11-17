package com.akifozdemir.jobservice.dtos;

import java.util.UUID;

public record JobResponse(UUID id,String title,String description,
                          String companyName,UUID companyId) {
}
