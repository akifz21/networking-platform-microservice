package com.akifozdemir.jobservice.mappers;

import com.akifozdemir.jobservice.dtos.JobApplicationRequest;
import com.akifozdemir.jobservice.dtos.JobApplicationResponse;
import com.akifozdemir.jobservice.dtos.UserResponse;
import com.akifozdemir.jobservice.models.JobApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {

    @Mappings({
            @Mapping(target = "id",source = "id"),
            @Mapping(target = "job.id",source = "jobId"),
    })
    JobApplication requestToJobApplication(JobApplicationRequest jobApplicationRequest);

    @Mappings({
            @Mapping(target = "id",source = "jobApplication.id"),
            @Mapping(target = "jobId",source = "jobApplication.job.id"),
            @Mapping(target = "jobName", source = "jobApplication.job.title"),
            @Mapping(target = "userFirstName",source = "user.firstName"),
            @Mapping(target = "userLastName",source = "user.lastName")
    })
    JobApplicationResponse jobApplicationToResponse(JobApplication jobApplication, UserResponse user);

}
