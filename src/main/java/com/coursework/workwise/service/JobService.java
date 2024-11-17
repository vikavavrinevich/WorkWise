package com.coursework.workwise.service;

import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.entity.Job;
import com.coursework.workwise.exception.JobNotFountException;
import com.coursework.workwise.mapper.JobMapper;
import com.coursework.workwise.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobDto getById(Long id){
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFountException("Job Not Found"));
        return jobMapper.toDto(job);
    }

    public List<JobDto> getAll(){
        List<Job> jobs = jobRepository.findAll();
        return  jobs.stream()
                .map(jobMapper::toDto)
                .toList();
    }

    @Transactional
    public JobDto create(JobCreationDto jobCreationDto){
        return jobMapper.toDto(jobRepository.save(jobMapper.toEntity(jobCreationDto)));
    }
}
