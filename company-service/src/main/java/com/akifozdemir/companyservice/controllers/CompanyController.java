package com.akifozdemir.companyservice.controllers;

import com.akifozdemir.companyservice.dtos.CompanyRequest;
import com.akifozdemir.companyservice.dtos.CompanyResponse;
import com.akifozdemir.companyservice.services.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class CompanyController {
    private final CompanyService companyService;
    public CompanyController(CompanyService companyService){
        this.companyService = companyService;
    }
    @PostMapping
    public ResponseEntity<String> add(@RequestBody CompanyRequest companyRequest){
        this.companyService.add(companyRequest);
        return ResponseEntity.ok().body("Company added");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        this.companyService.delete(id);
        return ResponseEntity.ok().body("Company deleted");
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll(){
        List<CompanyResponse> companyResponses = this.companyService.getAll();
        return ResponseEntity.ok().body(companyResponses);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<CompanyResponse>> getAllByOwner(@PathVariable UUID ownerId){
        List<CompanyResponse> companyResponses = this.companyService.getAllByOwner(ownerId);
        return ResponseEntity.ok().body(companyResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(this.companyService.getById(id));
    }
}
