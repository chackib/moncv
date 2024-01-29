package com.cronos.cvtool.service;

import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;

import com.cronos.cvtool.dto.openai.AiRequestConversationDto;
import com.cronos.cvtool.dto.openai.AiRequestSingleDto;
import com.cronos.cvtool.dto.openai.OpenAiResponseDto;

public interface OpenAiService {

	public ResponseEntity<OpenAiResponseDto> singleCallAsUser(AiRequestSingleDto request) throws URISyntaxException;
	public ResponseEntity<OpenAiResponseDto> conversationCall(AiRequestConversationDto request) throws URISyntaxException;
}
