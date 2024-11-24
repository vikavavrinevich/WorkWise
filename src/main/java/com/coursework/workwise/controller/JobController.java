package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.exception.JobNotFoundException;
import com.coursework.workwise.service.JobService;
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
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @GetMapping("{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(jobService.getById(id));
        } catch (JobNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<JobDto>> getAllJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Long companyId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "postedDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        if (!List.of("title", "salary", "postedDate").contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sortBy field: " + sortBy);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<JobDto> jobs = jobService.getAll(location, companyId, pageable);
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
//    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    public ResponseEntity<JobDto> createJob(@Valid @RequestBody JobCreationDto jobCreationDto) {
        return new ResponseEntity(jobService.create(jobCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long id, @RequestBody JobDto jobDto) {
        try {
            return new ResponseEntity(jobService.update(id, jobDto), HttpStatus.OK);
        } catch (JobNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYER')")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        try {
            jobService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (JobNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
