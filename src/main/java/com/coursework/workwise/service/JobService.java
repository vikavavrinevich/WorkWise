package com.coursework.workwise.service;

import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.entity.Job;
import com.coursework.workwise.exception.JobNotFoundException;
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
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job Not Found"));
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

    @Transactional
    public JobDto update(Long id, JobDto jobDto){
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job with id " + id + " not found"));
        jobMapper.partialUpdate(jobDto, job);
        job.setTitle(jobDto.title());
        job.setDescription(jobDto.description());
        job.setLocation(jobDto.location());
        job.setSalary(jobDto.salary());
        return jobMapper.toDto(jobRepository.save(job));
    }

    @Transactional
    public void delete(Long id){
        if(!jobRepository.existsById(id)){
            throw new JobNotFoundException("Job wit id " + " not found");
        }
        jobRepository.deleteById(id);
    }


}
