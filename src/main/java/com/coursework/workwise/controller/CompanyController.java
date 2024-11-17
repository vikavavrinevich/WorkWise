package com.coursework.workwise.controller;

import com.coursework.workwise.dto.CompanyCreationDto;
import com.coursework.workwise.dto.CompanyDto;
import com.coursework.workwise.dto.JobApplicationCreationDto;
import com.coursework.workwise.dto.JobApplicationDto;
import com.coursework.workwise.service.CompanyService;
import com.coursework.workwise.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createJobApplication(@Valid @RequestBody CompanyCreationDto companyCreationDto) {
        return new ResponseEntity(companyService.create(companyCreationDto), HttpStatus.CREATED);
    }
}
