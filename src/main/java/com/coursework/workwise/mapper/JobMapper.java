package com.coursework.workwise.mapper;

import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.entity.Job;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CompanyMapper.class})
public interface JobMapper {
    Job toEntity(JobDto jobDto);

    JobDto toDto(Job job);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Job partialUpdate(JobDto jobDto, @MappingTarget Job job);

    Job toEntity(JobCreationDto jobCreationDto);

}