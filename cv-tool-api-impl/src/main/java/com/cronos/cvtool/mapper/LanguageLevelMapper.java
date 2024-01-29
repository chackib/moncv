package com.cronos.cvtool.mapper;

import org.mapstruct.Mapper;

import com.cronos.cvtool.dto.candidate.LanguageLevelDto;
import com.cronos.cvtool.entity.candidate.LanguageLevel;

@Mapper
public interface LanguageLevelMapper {

	LanguageLevelDto entityToDto(LanguageLevel user);

}
