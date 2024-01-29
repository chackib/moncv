package com.cronos.cvtool.service;

import java.util.List;

import com.cronos.cvtool.dto.candidate.CountryDto;
import com.cronos.cvtool.dto.candidate.LanguageDto;
import com.cronos.cvtool.dto.candidate.LanguageLevelDto;

public interface UtilsService {

	public List<CountryDto> getCountries();
	public List<LanguageDto> getLanguages();
	public List<LanguageLevelDto> getLanguageLevels();

}
