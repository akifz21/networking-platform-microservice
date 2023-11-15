package com.akifozdemir.companyservice.dtos;

import java.util.UUID;

public record CompanyRequest(UUID userId,String name,String description) {
}
