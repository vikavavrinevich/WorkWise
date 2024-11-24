package com.coursework.workwise.mapper;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.entity.JobApplication;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {JobMapper.class, UserMapper.class})
public interface JobApplicationMapper {
    JobApplication toEntity(JobApplicationDto jobApplicationDto);

    JobApplicationDto toDto(JobApplication jobApplication);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    JobApplication partialUpdate(JobApplicationDto jobApplicationDto, @MappingTarget JobApplication jobApplication);

    @Mapping(target = "job", source = "job")
    @Mapping(target = "user", source = "user")
    JobApplication toEntity(JobApplicationCreationDto jobApplicationCreationDto);

}