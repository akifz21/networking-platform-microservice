package com.akifozdemir.companyservice.dtos;

import java.util.UUID;

public record CompanyRequest(
        UUID ownerId,String name,String description,
        String address,String email,String website) {
}
