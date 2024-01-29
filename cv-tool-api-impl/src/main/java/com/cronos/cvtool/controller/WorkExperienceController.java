package com.cronos.cvtool.controller;
import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.dto.candidate.WorkExperienceDto;
import com.cronos.cvtool.service.WorkExperienceService;
import com.cronos.cvtool.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@CrossOrigin
@Tag(name = "Work Experience", description = "The work experience API")
@RestController
@RequestMapping("/candidates/{idCandidate}/work-experiences")
public class WorkExperienceController {
    private final WorkExperienceService workExperienceService;
    public WorkExperienceController(WorkExperienceService workExperienceService) {
        this.workExperienceService = workExperienceService;
    }
    @Operation(summary = "Get work experiences list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Retrieved work experiences list",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = WorkExperienceDto.class)) }
            ),
            @ApiResponse(responseCode = "404", description = "No work experiences found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<ResponseDto> getAllWorkExperiences() {
        log.info("GET - Retrieved work experiences list");
        List<WorkExperienceDto> workExperiences = workExperienceService.getAllWorkExperiences();
        if(CollectionUtils.isEmpty(workExperiences)) {
            return Response.noContent();
        }
        return Response.ok(workExperiences);
    }
    @Operation(summary = "Get work experience by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Found the work experience",
                    content = { @Content( mediaType = "application/json", schema = @Schema(implementation = WorkExperienceDto.class)) }
            ),
            @ApiResponse(responseCode = "404", description = "Work experience not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getWorkExperienceById(@PathVariable String id) {
        log.info("GET - Find work experience with ID: {}", id);
        if(StringUtils.isNumeric(id)) {
            try {
                WorkExperienceDto workExperienceDto = workExperienceService.getWorkExperienceById(Long.parseLong(id));
                return Response.ok(workExperienceDto);
            }
            catch(EntityNotFoundException e) {
                log.warn("Work experience not found");
                return Response.noContent();
            }
        } else {
            log.warn("Parameter id should be numeric");
            return Response.badRequest("Parameter id should be numeric");
        }
    }

    @Operation(summary = "Update an existing work experience")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the work experience",
                    content = { @Content( mediaType = "application/json", schema = @Schema(implementation = WorkExperienceDto.class)) }
            ),
            @ApiResponse(responseCode = "404", description = "Work experience not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error updating the work experience", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateWorkExperience(@PathVariable Long id, @RequestBody WorkExperienceDto workExperienceDto) {
        WorkExperienceDto updatedWorkExperience = workExperienceService.updateWorkExperience(id, workExperienceDto);
        return Response.ok(updatedWorkExperience);
    }
    @Operation(summary = "Delete an existing work experience")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Work experience deleted",
                    content = { @Content( mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
            ),
            @ApiResponse(responseCode = "400", description = "Error deleting the work experience", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCandidate(@PathVariable Long id) {
        try {
            log.info("DELETE - Delete work experience with ID {}", id);
            workExperienceService.deleteWorkExperience(id);
            return Response.ok("The work experience was successfuly deleted");
        } catch(Exception e) {
            log.error("Error while deleting the work experience", e);
            return Response.badRequest("Error while deleting the work experience");
        }
    }
}