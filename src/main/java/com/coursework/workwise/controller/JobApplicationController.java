package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
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

    @PatchMapping("/{id}/approve")
    public ResponseEntity<JobApplicationDto> approveApplication(@PathVariable Long id) {
        JobApplicationDto updatedApplication = jobApplicationService.approveApplication(id);
        return ResponseEntity.ok(updatedApplication);
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<JobApplicationDto> rejectApplication(@PathVariable Long id) {
        JobApplicationDto updatedApplication = jobApplicationService.rejectApplication(id);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id){
        jobApplicationService.getById(id);
        return ResponseEntity.noContent().build();
    }
}
