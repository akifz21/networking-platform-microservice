package com.akifozdemir.companyservice.mappers;

import com.akifozdemir.companyservice.dtos.CompanyRequest;
import com.akifozdemir.companyservice.dtos.CompanyResponse;
import com.akifozdemir.companyservice.dtos.UserResponse;
import com.akifozdemir.companyservice.models.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company requestToComponent(CompanyRequest companyRequest);
    @Mappings({
            @Mapping(source = "user.id", target = "ownerId"),
            @Mapping(source = "company.id",target = "id"),
            @Mapping(source = "user.firstName",target = "ownerFirstName"),
            @Mapping(source = "user.lastName",target = "ownerLastName")
    })
    CompanyResponse companyToResponse(Company company, UserResponse user);
}
