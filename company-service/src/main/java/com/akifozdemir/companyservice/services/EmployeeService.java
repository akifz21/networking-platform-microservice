package com.akifozdemir.companyservice.services;

import com.akifozdemir.companyservice.client.UserClient;
import com.akifozdemir.companyservice.dtos.EmployeeRequest;
import com.akifozdemir.companyservice.dtos.EmployeeResponse;
import com.akifozdemir.companyservice.dtos.UserResponse;
import com.akifozdemir.companyservice.mappers.EmployeeMapper;
import com.akifozdemir.companyservice.models.Employee;
import com.akifozdemir.companyservice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserClient userClient;
    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper,UserClient userClient){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.userClient = userClient;
    }

    public void add(EmployeeRequest employeeRequest){
        Employee employee = this.employeeMapper.requestToEmployee(employeeRequest);
        this.employeeRepository.save(employee);
    }

    public List<EmployeeResponse> getByCompany(UUID companyId){
            List<Employee> employees = employeeRepository.findByCompany_Id(companyId);
            return employees.stream().map(employee -> {
                UserResponse userResponse = this.userClient.getUserById(employee.getUserId());
                return this.employeeMapper.employeeToResponse(employee,userResponse);
            }).collect(Collectors.toList());
    }


}
