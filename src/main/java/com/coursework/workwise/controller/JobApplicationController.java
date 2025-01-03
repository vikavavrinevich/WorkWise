package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.enums.ApplicationStatus;
import com.coursework.workwise.exception.JobApplicationNotFoundException;
import com.coursework.workwise.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Page<JobApplicationDto>> getAllJobApplications(
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(defaultValue = "user.name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (!List.of("user.name", "status").contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sortBy field: " + sortBy);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<JobApplicationDto> jobApplications = jobApplicationService.getAll(status, sortBy, sortDir, pageable);
        return ResponseEntity.ok(jobApplications);
    }

    @PostMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'JOBSEEKER')")
    public ResponseEntity<JobApplicationDto> createJobApplication(@Valid @RequestBody JobApplicationCreationDto jobApplicationCreationDto) {
        return new ResponseEntity(jobApplicationService.create(jobApplicationCreationDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/approve")
//    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    public ResponseEntity<JobApplicationDto> approveApplication(@PathVariable Long id) {
        try {
            JobApplicationDto updatedApplication = jobApplicationService.approveApplication(id);
            return ResponseEntity.ok(updatedApplication);
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}/reject")
//    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    public ResponseEntity<JobApplicationDto> rejectApplication(@PathVariable Long id) {
        try {
            JobApplicationDto updatedApplication = jobApplicationService.rejectApplication(id);
            return ResponseEntity.ok(updatedApplication);
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'JOBSEEKER')")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id){
        try {
            jobApplicationService.getById(id);
            return ResponseEntity.noContent().build();
        }catch (JobApplicationNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
