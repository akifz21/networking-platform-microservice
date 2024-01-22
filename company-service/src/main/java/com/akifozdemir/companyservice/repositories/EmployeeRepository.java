package com.akifozdemir.companyservice.repositories;


import com.akifozdemir.companyservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findByCompany_Id(UUID id);
}
