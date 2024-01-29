package com.cronos.cvtool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.openai.AiRequestConversationDto;
import com.cronos.cvtool.dto.openai.AiRequestSingleDto;
import com.cronos.cvtool.dto.openai.OpenAiResponseDto;
import com.cronos.cvtool.service.OpenAiService;
import com.cronos.cvtool.util.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@Tag(name = "OpenAI", description = "The OpenAI API")
@RestController
@RequestMapping("/openai")
public class OpenAiController {

	private OpenAiService service;
	
	public OpenAiController(OpenAiService service) {
		this.service = service;
	}

	@Operation(summary = "Create a single request to OpenAI")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Request to OpenAI was successful",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error creating the request to OpenAI", content = @Content)
	})
	@PostMapping(value = "/single")
	public ResponseEntity<ResponseDto> singleCall(@RequestBody AiRequestSingleDto aiRequest) {
		try {
			log.info("POST - Create a single request to OpenAI");

			ResponseEntity<OpenAiResponseDto> response = service.singleCallAsUser(aiRequest);

			return Response.ok(response.getBody(), "Request to OpenAI was successful");
		}
		catch(Exception e) {
			log.error("Error while creating request to OpenAI", e);
			return Response.badRequest("Error while creating request to OpenAI");
		}
	}

	@Operation(summary = "Create a conversation request to OpenAI")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Request to OpenAI was successful",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error creating the request to OpenAI", content = @Content)
	})
	@PostMapping(value = "/conversation")
	public ResponseEntity<ResponseDto> conversationCall(@RequestBody AiRequestConversationDto aiRequest) {
		try {
			log.info("POST - Create a conversation request to OpenAI");

			ResponseEntity<OpenAiResponseDto> response = service.conversationCall(aiRequest);

			return Response.ok(response.getBody(), "Request to OpenAI was successful");
		}
		catch(Exception e) {
			log.error("Error while creating request to OpenAI", e);
			return Response.badRequest("Error while creating request to OpenAI");
		}
	}

}
