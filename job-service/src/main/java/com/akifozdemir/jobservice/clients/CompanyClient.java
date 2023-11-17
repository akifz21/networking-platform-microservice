package com.akifozdemir.jobservice.clients;

import com.akifozdemir.jobservice.dtos.CompanyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "companies",url = "http://localhost:8030/companies")
public interface CompanyClient {
    @GetMapping("/{id}")
    CompanyResponse getCompany(@PathVariable UUID id);
}
