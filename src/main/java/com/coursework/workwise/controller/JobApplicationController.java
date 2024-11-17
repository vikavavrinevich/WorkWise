package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.service.JobApplicationService;
import com.coursework.workwise.service.JobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job_applications")
@AllArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("{id}")
    public ResponseEntity<JobApplicationDto> getJobApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(jobApplicationService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplications() {
        return ResponseEntity.ok(jobApplicationService.getAll());
    }

    @PostMapping
    public ResponseEntity<JobApplicationDto> createJobApplication(@Valid @RequestBody JobApplicationCreationDto jobApplicationCreationDto) {
        return new ResponseEntity(jobApplicationService.create(jobApplicationCreationDto), HttpStatus.CREATED);
    }
}
