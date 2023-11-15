package com.akifozdemir.companyservice.dtos;

import java.util.UUID;

public record CompanyResponse(UUID id,UUID userId,String name,String description,
                              String ownerFirstName,String ownerLastName ){}
