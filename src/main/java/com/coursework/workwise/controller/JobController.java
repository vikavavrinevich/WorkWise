package com.coursework.workwise.controller;

import com.coursework.workwise.dto.JobCreationDto;
import com.coursework.workwise.dto.JobDto;
import com.coursework.workwise.exception.JobNotFountException;
import com.coursework.workwise.service.JobService;
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

    @PutMapping("{id}")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long id, @RequestBody JobDto jobDto){
        try{
            return new ResponseEntity(jobService.update(id, jobDto), HttpStatus.OK);
        } catch (JobNotFountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id){
        try{
            jobService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (JobNotFountException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
