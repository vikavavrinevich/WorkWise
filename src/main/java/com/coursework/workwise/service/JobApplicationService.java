package com.coursework.workwise.service;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.entity.JobApplication;
import com.coursework.workwise.enums.ApplicationStatus;
import com.coursework.workwise.exception.JobApplicationNotFoundException;
import com.coursework.workwise.exception.ResumeNotFoundException;
import com.coursework.workwise.mapper.JobApplicationMapper;
import com.coursework.workwise.repository.JobApplicationRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper jobApplicationMapper;

    public JobApplicationDto getById(Long id){
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException("Job application not found"));
        return jobApplicationMapper.toDto(jobApplication);
    }

    public Page<JobApplicationDto> getAll(ApplicationStatus status, String sortBy, String sortDir, Pageable pageable) {
        Specification<JobApplication> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            return predicates.isEmpty() ? null : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<JobApplication> jobApplications = jobApplicationRepository.findAll(specification, sortedPageable);
        return jobApplications.map(jobApplicationMapper::toDto);
    }

    @Transactional
    public JobApplicationDto create(JobApplicationCreationDto jobApplicationCreationDto){
        JobApplication jobApplication = jobApplicationMapper.toEntity(jobApplicationCreationDto);
        jobApplication.setStatus(ApplicationStatus.PENDING);
        return jobApplicationMapper.toDto(jobApplicationRepository.save(jobApplication));
    }

    @Transactional
    public JobApplicationDto approveApplication(Long id){
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException("Job application not found with id: " + id));
        jobApplication.setStatus(ApplicationStatus.ACCEPTED);
        return jobApplicationMapper.toDto(jobApplication);
    }

    @Transactional
    public JobApplicationDto rejectApplication(Long id){
        JobApplication jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new JobApplicationNotFoundException("Job application not found with id: " + id));
        jobApplication.setStatus(ApplicationStatus.REJECTED);
        return jobApplicationMapper.toDto(jobApplication);
    }

    @Transactional
    public void delete(Long id){
        if(!jobApplicationRepository.existsById(id)){
            throw new ResumeNotFoundException("Resume with id " + " not found");
        }
        jobApplicationRepository.deleteById(id);
    }

}
