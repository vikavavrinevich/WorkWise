package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.dto.ResumeCreationDto;
import com.coursework.workwise.dto.ResumeDto;
import com.coursework.workwise.service.JobService;
import com.coursework.workwise.service.ResumeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAll());
    }

    @PostMapping
    public ResponseEntity<JobDto> createJob(@Valid @RequestBody JobCreationDto jobCreationDto) {
        return new ResponseEntity(jobService.create(jobCreationDto), HttpStatus.CREATED);
    }
}
