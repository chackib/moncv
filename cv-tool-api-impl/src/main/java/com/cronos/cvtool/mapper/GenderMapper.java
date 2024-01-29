package com.cronos.cvtool.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cronos.cvtool.dto.candidate.GenderDto;
import com.cronos.cvtool.entity.candidate.Gender;

public interface GenderMapper {
	
	 GenderMapper INSTANCE = Mappers.getMapper(GenderMapper.class);

	    @Mapping(target = "code", source = "gender.code")
	    GenderDto toDto(Gender gender);

}
