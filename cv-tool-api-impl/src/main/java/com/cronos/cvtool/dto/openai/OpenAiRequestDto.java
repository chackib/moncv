package com.cronos.cvtool.dto.openai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiRequestDto {

	@JsonProperty("model")
	private String model;
	@JsonProperty("messages")
	private List<AiMessage> messages;

}
