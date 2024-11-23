package com.coursework.workwise.service;

import com.coursework.workwise.dto.CompanyCreationDto;
import com.coursework.workwise.dto.CompanyDto;
import com.coursework.workwise.entity.Company;
import com.coursework.workwise.exception.CompanyAlreadyExistException;
import com.coursework.workwise.exception.CompanyNotFoundException;
import com.coursework.workwise.mapper.CompanyMapper;
import com.coursework.workwise.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyDto getById(Long id){
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + id + "not found"));
        return companyMapper.toDto(company);
    }

    public List<CompanyDto> getAll(){
        List<Company> companies = companyRepository.findAll();
        return  companies.stream()
                .map(companyMapper::toDto)
                .toList();
    }

    @Transactional
    public CompanyDto create(CompanyCreationDto companyCreationDto){
        if(companyRepository.existsByName(companyCreationDto.name())){
            throw new CompanyAlreadyExistException("Company: " + companyCreationDto.name() + " already exist");
        }
        return companyMapper.toDto(companyRepository.save(companyMapper.toEntity(companyCreationDto)));
    }

    @Transactional
    public CompanyDto update(Long id, CompanyDto companyDto){
        Company company = companyRepository.findById(id).
                orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        if(companyRepository.existsByName(companyDto.name())){
            throw new CompanyAlreadyExistException("Company: " + companyDto.name() + " already exist");
        }
        companyMapper.partialUpdate(companyDto, company);
        company.setName(companyDto.name());
        company.setIndustry(companyDto.industry());
        company.setDescription(companyDto.description());
        company.setLocation(companyDto.location());
        return companyMapper.toDto(companyRepository.save(company));
    }

    @Transactional
    public void delete(Long id){
        if(!companyRepository.existsById(id)){
            throw new CompanyNotFoundException("Company with id " + id + "not found");
        }
        companyRepository.deleteById(id);
    }
}
