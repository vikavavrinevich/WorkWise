package com.coursework.workwise.service;

import com.coursework.workwise.dto.ResumeCreationDto;
import com.coursework.workwise.dto.ResumeDto;
import com.coursework.workwise.entity.Resume;
import com.coursework.workwise.exception.ResumeAlreadyExistsException;
import com.coursework.workwise.exception.ResumeNotFoundException;
import com.coursework.workwise.mapper.ResumeMapper;
import com.coursework.workwise.repository.ResumeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final ResumeMapper resumeMapper;

    public ResumeDto getById(Long id){
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume Not Found"));
        return resumeMapper.toDto(resume);
    }

    public List<ResumeDto> getAll(){
        List<Resume> resumes = resumeRepository.findAll();
        return  resumes.stream()
                .map(resumeMapper::toDto)
                .toList();
    }

    @Transactional
    public ResumeDto create(ResumeCreationDto resumeCreationDto){
        return resumeMapper.toDto(resumeRepository.save(resumeMapper.toEntity(resumeCreationDto)));
    }

    @Transactional
    public  ResumeDto update(Long id, ResumeDto resumeDto){
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume Not Found"));
        resumeMapper.partialUpdate(resumeDto, resume);
        resume.setEducation(resumeDto.education());
        resume.setExperience(resumeDto.experience());
        resume.setSummary(resumeDto.summary());
        resume.setSkills(resumeDto.skills());
        return  resumeMapper.toDto(resumeRepository.save(resume));
    }

    @Transactional
    public void delete(Long id){
        if(!resumeRepository.existsById(id)){
            throw new ResumeNotFoundException("Resume with id " + " not found");
        }
        resumeRepository.deleteById(id);
    }
}
