package com.akifozdemir.companyservice.controllers;

import com.akifozdemir.companyservice.dtos.CompanyRequest;
import com.akifozdemir.companyservice.dtos.CompanyResponse;
import com.akifozdemir.companyservice.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }
    @PostMapping
    public ResponseEntity<String> add(CompanyRequest companyRequest){
        this.companyService.add(companyRequest);
        return ResponseEntity.ok().body("Company added");
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll(){
        List<CompanyResponse> companyResponses = this.companyService.getAll();
        return ResponseEntity.ok().body(companyResponses);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CompanyResponse>> getAllByOwner(UUID ownerId){
        List<CompanyResponse> companyResponses = this.companyService.getAllByOwner(ownerId);
        return ResponseEntity.ok().body(companyResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(UUID id){
        return ResponseEntity.ok().body(this.companyService.getById(id));
    }




}
