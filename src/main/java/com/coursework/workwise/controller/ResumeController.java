package com.coursework.workwise.controller;

import com.coursework.workwise.dto.ResumeCreationDto;
import com.coursework.workwise.dto.ResumeDto;
import com.coursework.workwise.dto.UserDto;
import com.coursework.workwise.exception.ResumeAlreadyExistsException;
import com.coursework.workwise.exception.ResumeNotFoundException;
import com.coursework.workwise.service.ResumeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@AllArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(resumeService.getById(id));
        }catch (ResumeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'JOBSEEKER')")
    public ResponseEntity<ResumeDto> createResume(@Valid @RequestBody ResumeCreationDto resumeCreationDto) {
        try {
            return new ResponseEntity(resumeService.create(resumeCreationDto), HttpStatus.CREATED);
        }catch (ResumeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JOBSEEKER')")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long id, @RequestBody ResumeDto resumeDto){
        try {
            return new ResponseEntity(resumeService.update(id, resumeDto), HttpStatus.OK);
        }catch (ResumeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'JOBSEEKER')")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id){
        try {
            resumeService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (ResumeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<ResumeDto> getResumeByUser(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok(resumeService.findByUser(userDto));
        } catch (ResumeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}