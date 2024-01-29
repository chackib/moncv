package com.cronos.cvtool.controller;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.dto.candidate.ProfileDto;
import com.cronos.cvtool.dto.candidate.WorkExperienceDto;
import com.cronos.cvtool.exception.MyResourceNotFoundException;
import com.cronos.cvtool.service.CandidateService;
import com.cronos.cvtool.service.WorkExperienceService;
import com.cronos.cvtool.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@Tag(name = "Candidate", description = "The candidate API")
@RestController
@RequestMapping("/candidates")
public class CandidateController {

    private final CandidateService candidateService;
	private final WorkExperienceService workExperienceService;

    public CandidateController(CandidateService candidateService, WorkExperienceService workExperienceService) {
        this.candidateService = candidateService;
		this.workExperienceService = workExperienceService;
    }
    
   @Operation(summary = "Get candidates list")
   @ApiResponses(value = {
		   @ApiResponse(
				   responseCode = "200", description = "Retrieved candidates list",
				   content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
		   ),
		   @ApiResponse(responseCode = "204", description = "No candidates found", content = @Content)
   })
   @GetMapping("/allInformation")
   public ResponseEntity<Page<CandidateDto>> getAllCandidates(
		   @RequestParam(name = "page", defaultValue = "0") int page,
		   @RequestParam(name = "size", defaultValue = "2") int size,
		   @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {

	   log.info("GET - Retrieving candidates list (Page: {}, Size: {}, SortBy: {})", page, size, sortBy);

	   Page<CandidateDto> candidatesPage = candidateService.getAllCandidates(page, size, sortBy);

	   if (candidatesPage.isEmpty()) {
		   log.info("GET - No candidates found");
		   return ResponseEntity.noContent().build();
	   }

	   log.info("GET - Candidates retrieved successfully");
	   return ResponseEntity.ok(candidatesPage);
   }

	@Operation(summary = "Get candidates list")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", description = "Retrieved candidates list",
					content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
			),
			@ApiResponse(responseCode = "204", description = "No candidates found", content = @Content)
	})
	@GetMapping
	public ResponseEntity<Page<ProfileDto>> getAllProfiles(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {

		log.info("GET - Retrieving candidates list (Page: {}, Size: {}, SortBy: {})", page, size, sortBy);

		Page<ProfileDto> candidatesPage = candidateService.getAllProfiles(page, size, sortBy);

		if (candidatesPage.isEmpty()) {
			log.info("GET - No candidates found");
			return ResponseEntity.noContent().build();
		}

		log.info("GET - Candidates retrieved successfully");
		return ResponseEntity.ok(candidatesPage);
	}

    @Operation(summary = "Get candidate by ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Found the candidate",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
		),
		@ApiResponse(responseCode = "404", description = "Candidate not found", content = @Content),
		@ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content)
	})
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getCandidateById(@PathVariable String id) {
    	log.info("GET - Find candidate with ID {}", id);
		if(StringUtils.isNumeric(id)) {
			try {
				CandidateDto candidateDto = candidateService.getCandidateById(Long.parseLong(id));
				return Response.ok(candidateDto);
			}
			catch(MyResourceNotFoundException e) {
				log.warn("Candidate not found with id : " + id);
				return Response.noContent();
			}
		} else {
			log.warn("Parameter id should be numeric");
			return Response.badRequest("Parameter id should be numeric");
		}
    }

    @Operation(summary = "Create a new candidate")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created the candidate",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error creating the candidate", content = @Content),
	})
    @PostMapping
    public ResponseEntity<ResponseDto> createCandidate(@RequestBody CandidateDto candidateDto) {
    	try {
			log.info("POST - Create new candidate");
	        CandidateDto createdCandidate = candidateService.createCandidate(candidateDto);
	        return Response.ok(createdCandidate);
    	} catch(Exception e) {
			log.error("Error while creating new candidate", e);
			return Response.badRequest("Error while creating new candidate");
		}
    }

  //TODO manage the different criteria for updating a candidate
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateCandidate(@PathVariable Long id, @RequestBody CandidateDto candidateDto) {
        CandidateDto updatedCandidate = candidateService.updateCandidate(id, candidateDto);
        return Response.ok(updatedCandidate);
    }

    @Operation(summary = "Delete an existing candidate")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Deleted the candidate",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error deleting the candidate", content = @Content)
	})
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCandidate(@PathVariable Long id) {
    	try {
			log.info("DELETE - Delete candidate with ID {}", id);
	        candidateService.deleteCandidate(id);
	        return Response.ok("The candidate was successfully deleted");
    	} catch(Exception e) {
			log.error("Error while deleting the candidate", e);
			return Response.badRequest("Error while deleting the candidate");
		}
    }

	@Operation(summary = "Add a new work experience")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "work experience created",
					content = { @Content( mediaType = "application/json", schema = @Schema(implementation = WorkExperienceDto.class)) }
			),
			@ApiResponse(responseCode = "400", description = "Error creating the work experience", content = @Content),
	})
	@PostMapping("/{idCandidate}/WorkExperience")
	public ResponseEntity<ResponseDto> createWorkExperience(@RequestBody WorkExperienceDto workExperienceDto) {
		try {
			log.info("POST - Create new work experience");
			WorkExperienceDto createdWorkExperience = workExperienceService.createWorkExperience(workExperienceDto);
			return Response.ok(createdWorkExperience);
		} catch(Exception e) {
			log.error("Error while creating new work experience", e);
			return Response.badRequest("Error while creating new work experience");
		}
	}
}
