package com.akifozdemir.companyservice.dtos;

import java.util.UUID;

public record CompanyResponse(
        UUID id,UUID ownerId,
        String name,String description,
        String ownerFirstName,String ownerLastName,
        String address,String email,String website
){}
