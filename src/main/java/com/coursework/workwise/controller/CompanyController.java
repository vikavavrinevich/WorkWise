package com.coursework.workwise.controller;

import com.coursework.workwise.dto.CompanyCreationDto;
import com.coursework.workwise.dto.CompanyDto;
import com.coursework.workwise.exception.CompanyAlreadyExistException;
import com.coursework.workwise.exception.CompanyNotFoundException;
import com.coursework.workwise.service.CompanyService;
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
        try {
            return ResponseEntity.ok(companyService.getById(id));
        }catch (CompanyNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyCreationDto companyCreationDto) {
        try {
            return new ResponseEntity(companyService.create(companyCreationDto), HttpStatus.CREATED);
        }catch (CompanyAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        try{
            return new ResponseEntity(companyService.update(id, companyDto), HttpStatus.OK);
        } catch (CompanyNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }catch (CompanyAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
        try{
            companyService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (CompanyNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
