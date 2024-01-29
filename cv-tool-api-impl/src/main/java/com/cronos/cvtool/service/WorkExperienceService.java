package com.cronos.cvtool.service;
import com.cronos.cvtool.dto.candidate.WorkExperienceDto;
import java.util.List;
public interface WorkExperienceService {
    List<WorkExperienceDto> getAllWorkExperiences();
    WorkExperienceDto getWorkExperienceById(Long id);
    WorkExperienceDto createWorkExperience(WorkExperienceDto workExperienceDto);
    WorkExperienceDto updateWorkExperience(Long id, WorkExperienceDto workExperienceDto);
    void deleteWorkExperience(Long id);
}