package com.cronos.cvtool.service;
import com.cronos.cvtool.dto.candidate.WorkExperienceDto;
import com.cronos.cvtool.entity.candidate.WorkExperience;
import com.cronos.cvtool.mapper.WorkExperienceMapper;
import com.cronos.cvtool.repository.CandidateRepository;
import com.cronos.cvtool.repository.WorkExperienceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final WorkExperienceRepository workExperienceRepository;
    private final CandidateRepository candidateRepository;
    private final WorkExperienceMapper workExperienceMapper = Mappers.getMapper(WorkExperienceMapper.class);
    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository,
                                     CandidateRepository candidateRepository) {
        this.workExperienceRepository = workExperienceRepository;
        this.candidateRepository = candidateRepository;
    }
    @Override
    public List<WorkExperienceDto> getAllWorkExperiences() {
        return workExperienceRepository.findAll().stream()
                .map(workExperienceMapper::entityToDto)
                .toList();
    }
    @Override
    public WorkExperienceDto getWorkExperienceById(Long id) {
        WorkExperience workExperience = workExperienceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Work experience not found with id: " + id));
        return workExperienceMapper.entityToDto(workExperience);
    }
    @Override
    public WorkExperienceDto createWorkExperience(WorkExperienceDto workExperienceDto) {
        WorkExperience workExperience = workExperienceMapper.dtoToEntity(workExperienceDto);
        workExperience.setCandidate(this.candidateRepository.findById(workExperienceDto.getCandidateId())
                .orElseThrow(() -> new EntityNotFoundException("candidate not found with id : " + workExperienceDto.getCandidateId())));
        WorkExperience savedWorkExperience = workExperienceRepository.save(workExperience);
        return workExperienceMapper.entityToDto(savedWorkExperience);
    }
    @Override
    public WorkExperienceDto updateWorkExperience(Long id, WorkExperienceDto workExperienceDto) {
        return null;
    }
    @Override
    public void deleteWorkExperience(Long id) {
        workExperienceRepository.deleteById(id);
    }
}