package com.akifozdemir.jobservice.dtos;

import java.util.UUID;

public record JobRequest (String title, String description, UUID companyId){
}
