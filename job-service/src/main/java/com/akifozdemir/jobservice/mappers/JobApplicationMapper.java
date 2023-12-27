package com.akifozdemir.jobservice.mappers;

import com.akifozdemir.jobservice.dtos.JobApplicationRequest;
import com.akifozdemir.jobservice.dtos.JobApplicationResponse;
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
            @Mapping(target = "jobId",source = "job.id")
    })
    JobApplicationResponse jobApplicationToResponse(JobApplication jobApplication);

}
