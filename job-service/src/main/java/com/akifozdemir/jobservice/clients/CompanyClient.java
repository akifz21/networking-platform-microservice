package com.akifozdemir.jobservice.clients;

import com.akifozdemir.jobservice.dtos.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "company",url = "http://localhost:8181")
public interface CompanyClient {
    @GetMapping("/{id}")
    CompanyResponse getCompany(@PathVariable UUID id);
}
