package com.cronos.cvtool.mapper;

import com.cronos.cvtool.dto.candidate.WorkExperienceDto;
import com.cronos.cvtool.entity.candidate.WorkExperience;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface WorkExperienceMapper {
    WorkExperienceMapper INSTANCE = Mappers.getMapper(WorkExperienceMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "candidate.id",target = "candidateId")
    @Mapping(source = "usefulLinks", target = "usefulLinks")
    @Mapping(source = "isOnGoing", target = "isOnGoing")
    WorkExperienceDto entityToDto(WorkExperience workExperience);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "usefulLinks", target = "usefulLinks")
    @Mapping(source = "isOnGoing", target = "isOnGoing")
    WorkExperience dtoToEntity(WorkExperienceDto workExperienceDto);

}

