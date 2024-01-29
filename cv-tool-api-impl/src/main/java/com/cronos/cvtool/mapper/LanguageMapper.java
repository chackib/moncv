package com.cronos.cvtool.mapper;

import org.mapstruct.Mapper;

import com.cronos.cvtool.dto.candidate.LanguageDto;
import com.cronos.cvtool.entity.candidate.Language;

@Mapper
public interface LanguageMapper {

	LanguageDto entityToDto(Language user);

}
