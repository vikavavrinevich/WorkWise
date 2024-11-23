package com.coursework.workwise.controller;

import com.coursework.workwise.dto.ResumeCreationDto;
import com.coursework.workwise.dto.ResumeDto;
import com.coursework.workwise.service.ResumeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
@AllArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAll());
    }

    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@Valid @RequestBody ResumeCreationDto resumeCreationDto) {
        return new ResponseEntity(resumeService.create(resumeCreationDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long id, @RequestBody ResumeDto resumeDto){
        return new ResponseEntity(resumeService.update(id, resumeDto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id){
        resumeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}