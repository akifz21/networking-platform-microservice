package com.akifozdemir.companyservice.services;

import com.akifozdemir.companyservice.client.UserClient;
import com.akifozdemir.companyservice.dtos.CompanyRequest;
import com.akifozdemir.companyservice.dtos.CompanyResponse;
import com.akifozdemir.companyservice.dtos.UserResponse;
import com.akifozdemir.companyservice.exceptions.CompanyNotFoundException;
import com.akifozdemir.companyservice.exceptions.FetchException;
import com.akifozdemir.companyservice.mappers.CompanyMapper;
import com.akifozdemir.companyservice.models.Company;
import com.akifozdemir.companyservice.repositories.CompanyRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserClient userClient;
    public CompanyService(CompanyRepository companyRepository,
                          CompanyMapper companyMapper,UserClient userClient){
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.userClient = userClient;
    }
    public void add(CompanyRequest companyRequest){
        Company company = this.companyMapper.requestToComponent(companyRequest);
        company.setWorkers(new ArrayList<>());
        this.companyRepository.save(company);
    }

    public void delete(UUID id){
        this.companyRepository.deleteById(id);

    }

    public void updateWorkers(UUID companyId, List<UUID> workerIds) {
        Company company = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found."));
        company.addWorkers(workerIds);
        this.companyRepository.save(company);
    }

    public CompanyResponse getById(UUID id){
        Company company = this.companyRepository.findById(id).
                orElseThrow(()-> new CompanyNotFoundException());
        UserResponse userResponse = userClient.getUserById(company.getOwnerId());
        return this.companyMapper.companyToResponse(company,userResponse);
    }

    public List<CompanyResponse> getAll(){
        try {
            List<Company> companyList = this.companyRepository.findAll();
            return companyList.stream().map(company -> {
                UserResponse userResponse = userClient.getUserById(company.getOwnerId());
                return this.companyMapper.companyToResponse(company,userResponse);
            }).collect(Collectors.toList());
        }catch (FeignException e){
            throw new FetchException();
        }
    }

    public List<CompanyResponse> getAllByOwner(UUID ownerId){
        try {
            UserResponse userResponse = this.userClient.getUserById(ownerId);
            List<Company> companyList = this.companyRepository.findByOwnerId(ownerId);
            return companyList.stream().map(company ->
                    this.companyMapper.companyToResponse(company,userResponse)
            ).collect(Collectors.toList());
        }catch (FeignException e){
            throw new FetchException();
        }
    }


}
