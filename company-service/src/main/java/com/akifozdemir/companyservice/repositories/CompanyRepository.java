package com.akifozdemir.companyservice.repositories;

import com.akifozdemir.companyservice.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    List<Company> findByOwnerId(UUID ownerId);
}
