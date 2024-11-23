package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.exception.JobApplicationNotFoundException;
import com.coursework.workwise.exception.JobNotFoundException;
import com.coursework.workwise.service.JobApplicationService;
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
        try {
            return ResponseEntity.ok(jobApplicationService.getById(id));
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationDto>> getAllJobApplications() {
        return ResponseEntity.ok(jobApplicationService.getAll());
    }

    @PostMapping
    public ResponseEntity<JobApplicationDto> createJobApplication(@Valid @RequestBody JobApplicationCreationDto jobApplicationCreationDto) {
        return new ResponseEntity(jobApplicationService.create(jobApplicationCreationDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<JobApplicationDto> approveApplication(@PathVariable Long id) {
        try {
            JobApplicationDto updatedApplication = jobApplicationService.approveApplication(id);
            return ResponseEntity.ok(updatedApplication);
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<JobApplicationDto> rejectApplication(@PathVariable Long id) {
        try {
            JobApplicationDto updatedApplication = jobApplicationService.rejectApplication(id);
            return ResponseEntity.ok(updatedApplication);
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id){
        try {
            jobApplicationService.getById(id);
            return ResponseEntity.noContent().build();
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
