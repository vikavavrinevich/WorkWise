package com.coursework.workwise.service;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.entity.JobApplication;
import com.coursework.workwise.enums.ApplicationStatus;
import com.coursework.workwise.exception.JobApplicationNotFoundException;
import com.coursework.workwise.mapper.JobApplicationMapper;
import com.coursework.workwise.repository.JobApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<JobApplicationDto> getAll(){
        List<JobApplication> jobApplications = jobApplicationRepository.findAll();
        return  jobApplications.stream()
                .map(jobApplicationMapper::toDto)
                .toList();
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
        jobApplicationRepository.deleteById(id);
    }

}
