package com.cronos.cvtool.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cronos.cvtool.dto.openai.AiMessage;
import com.cronos.cvtool.dto.openai.AiRequestConversationDto;
import com.cronos.cvtool.dto.openai.AiRequestSingleDto;
import com.cronos.cvtool.dto.openai.OpenAiRequestDto;
import com.cronos.cvtool.dto.openai.OpenAiResponseDto;

@Service
public class OpenAiServiceImpl implements OpenAiService {

	@Autowired
	private RestTemplate restTemplate;

	@Value( "${openapi.url}" )
	private String openApiUrl;

	@Value( "${openapi.model}" )
	private String openApiModel;

	@Override
	public ResponseEntity<OpenAiResponseDto> singleCallAsUser(AiRequestSingleDto request) throws URISyntaxException {
		// Map AiRequest to OpenApiRequest
		OpenAiRequestDto openApiRequestDto = OpenAiRequestDto.builder()
			.model(openApiModel)
			.messages(request.getMessages().stream()
				.map(msg -> AiMessage.builder()
					.role("user")
					.content(msg)
					.build()
				)
				.collect(Collectors.toList())
			)
			.build();

		// Call OpenAPI
		return this.callOpenApi(openApiRequestDto);
	}

	@Override
	public ResponseEntity<OpenAiResponseDto> conversationCall(AiRequestConversationDto request) throws URISyntaxException {
		// Map AiRequest to OpenApiRequest
		OpenAiRequestDto openApiRequestDto = OpenAiRequestDto.builder()
			.model(openApiModel)
			.messages(request.getMessages())
			.build();

		// Call OpenAPI
		return this.callOpenApi(openApiRequestDto);
	}

	private ResponseEntity<OpenAiResponseDto> callOpenApi(OpenAiRequestDto request) throws URISyntaxException {
		// Prepare the request
		RequestEntity<OpenAiRequestDto> requestEntity = RequestEntity
			.post(new URI(openApiUrl))
			.accept(MediaType.APPLICATION_JSON)
			.body(request);

		// Call OpenAPI
		return restTemplate.exchange(requestEntity, OpenAiResponseDto.class);
	}
}
