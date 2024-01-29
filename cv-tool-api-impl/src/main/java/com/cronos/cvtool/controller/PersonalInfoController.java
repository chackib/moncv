package com.cronos.cvtool.controller;


import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.PersonalInfoDto;
import com.cronos.cvtool.service.PersonalInfoService;
import com.cronos.cvtool.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@Tag(name = "PersonalInfo", description = "The personal infos API")
@RestController
@RequestMapping("/candidates/{idCandidate}/personal-info")
public class PersonalInfoController {
	
	private final PersonalInfoService personalInfoService;

	public PersonalInfoController(PersonalInfoService personalInfoService) {
		super();
		this.personalInfoService = personalInfoService;
	}
	
	@Operation(summary = "Get personal infos of a candidate by ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Found the candidate",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = PersonalInfoDto.class)) }
		),
		@ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content),
	})
	@GetMapping
    public ResponseEntity<ResponseDto> getPersonalInfo(@PathVariable Long idCandidate) {
		log.info("GET - Retrieved personal info for candidate with ID {}", idCandidate);
		PersonalInfoDto personalInfo = personalInfoService.getPersonalInfo(idCandidate);
		if(personalInfo == null) {
			return Response.noContent();
		}
		return Response.ok(personalInfo);
    }

	@Operation(summary = "Update personal infos of a candidate by ID")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", description = "Updated the personal infos",
					content = { @Content( mediaType = "application/json", schema = @Schema(implementation = PersonalInfoDto.class)) }
			),
			@ApiResponse(responseCode = "400", description = "Error updating the personal infos", content = @Content)
	})
	@PutMapping
	public ResponseEntity<ResponseDto> updatePersonalInfo(@PathVariable Long idCandidate, @RequestBody PersonalInfoDto personalInfoDto) {
		try {
			log.info("PUT - Update personal infos of a candidate with ID {}", idCandidate);
			PersonalInfoDto personalInfo = personalInfoService.updatePersonalInfo(idCandidate, personalInfoDto);
			return Response.ok(personalInfo);
		}
		catch(Exception e) {
			log.error("Error while updating the personal infos", e);
			return Response.badRequest("Error while updating the personal infos");
		}
	}

}
