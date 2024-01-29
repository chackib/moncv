package com.cronos.cvtool.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cronos.cvtool.dto.candidate.CountryDto;
import com.cronos.cvtool.dto.candidate.LanguageDto;
import com.cronos.cvtool.dto.candidate.LanguageLevelDto;
import com.cronos.cvtool.mapper.CountryMapper;
import com.cronos.cvtool.mapper.LanguageLevelMapper;
import com.cronos.cvtool.mapper.LanguageMapper;
import com.cronos.cvtool.repository.CountryRepository;
import com.cronos.cvtool.repository.LanguageLevelRepository;
import com.cronos.cvtool.repository.LanguageRepository;

@Service
public class UtilsServiceImpl implements UtilsService {

	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private LanguageLevelRepository languageLevelRepository;

	@Cacheable("countries")
	@Override
	public List<CountryDto> getCountries() {
		CountryMapper mapper = Mappers.getMapper(CountryMapper.class);
		return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
			.map(country -> mapper.entityToDto(country))
			.collect(Collectors.toList());
	}

	@Cacheable("languages")
	@Override
	public List<LanguageDto> getLanguages() {
		LanguageMapper mapper = Mappers.getMapper(LanguageMapper.class);
		return StreamSupport.stream(languageRepository.findAll().spliterator(), false)
				.map(country -> mapper.entityToDto(country))
				.collect(Collectors.toList());
	}

	@Cacheable("languagelevels")
	@Override
	public List<LanguageLevelDto> getLanguageLevels() {
		LanguageLevelMapper mapper = Mappers.getMapper(LanguageLevelMapper.class);
		return StreamSupport.stream(languageLevelRepository.findAll().spliterator(), false)
				.map(country -> mapper.entityToDto(country))
				.collect(Collectors.toList());
	}

}
