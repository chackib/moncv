package com.cronos.cvtool.controller;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.CountryDto;
import com.cronos.cvtool.dto.candidate.LanguageDto;
import com.cronos.cvtool.dto.candidate.LanguageLevelDto;
import com.cronos.cvtool.service.UtilsService;
import com.cronos.cvtool.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@Tag(name = "Utils", description = "The utils API")
@RestController
@RequestMapping("/utils")
public class UtilsController {

	private UtilsService service;
	
	public UtilsController(UtilsService service) {
		this.service = service;
	}

	@Operation(summary = "Get countries list")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Retrieved countries list",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CountryDto.class)) }
		),
		@ApiResponse(responseCode = "204", description = "No countries found", content = @Content)
	})
	@GetMapping(value = "/country")
	public ResponseEntity<ResponseDto> getCountries() {
		log.info("GET - Retrieved countries list");
		List<CountryDto> countries = service.getCountries();

		if(CollectionUtils.isEmpty(countries)) {
			return Response.noContent();
		}

		return Response.ok(countries);
	}

	@Operation(summary = "Get languages list")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Retrieved languages list",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageDto.class)) }
		),
		@ApiResponse(responseCode = "204", description = "No languages found", content = @Content)
	})
	@GetMapping(value = "/language")
	public ResponseEntity<ResponseDto> getLanguages() {
		log.info("GET - Retrieved languages list");
		List<LanguageDto> languages = service.getLanguages();

		if(CollectionUtils.isEmpty(languages)) {
			return Response.noContent();
		}

		return Response.ok(languages);
	}

	@Operation(summary = "Get language levels list")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Retrieved language levels list",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LanguageLevelDto.class)) }
		),
		@ApiResponse(responseCode = "204", description = "No language levels found", content = @Content)
	})
	@GetMapping(value = "/languagelevel")
	public ResponseEntity<ResponseDto> getLanguageLevels() {
		log.info("GET - Retrieved language levels list");
		List<LanguageLevelDto> languageLevels = service.getLanguageLevels();

		if(CollectionUtils.isEmpty(languageLevels)) {
			return Response.noContent();
		}

		return Response.ok(languageLevels);
	}

}
