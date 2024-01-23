package com.akifozdemir.companyservice.controllers;

import com.akifozdemir.companyservice.dtos.EmployeeRequest;
import com.akifozdemir.companyservice.dtos.EmployeeResponse;
import com.akifozdemir.companyservice.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService)  {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody EmployeeRequest employeeRequest){
        this.employeeService.add(employeeRequest);
        return ResponseEntity.ok().body("Employee Registered Successfully");
    }
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<EmployeeResponse>> getByCompany(@PathVariable UUID companyId){
        return ResponseEntity.ok().body(this.employeeService.getByCompany(companyId));
    }
}
