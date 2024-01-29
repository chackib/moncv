package com.cronos.cvtool.mapper;

import org.mapstruct.Mapper;

import com.cronos.cvtool.dto.candidate.CountryDto;
import com.cronos.cvtool.entity.candidate.Country;

@Mapper
public interface CountryMapper {

	CountryDto entityToDto(Country user);

}
