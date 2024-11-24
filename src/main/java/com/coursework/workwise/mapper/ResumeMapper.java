package com.coursework.workwise.mapper;

import com.coursework.workwise.dto.ResumeCreationDto;
import com.coursework.workwise.dto.ResumeDto;
import com.coursework.workwise.entity.Resume;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface ResumeMapper {
    Resume toEntity(ResumeDto resumeDto);

    ResumeDto toDto(Resume resume);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Resume partialUpdate(ResumeDto resumeDto, @MappingTarget Resume resume);

    @Mapping(target = "user", source = "user")
    Resume toEntity(ResumeCreationDto resumeCreationDto);

}