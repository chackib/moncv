package com.cronos.cvtool.dto.openai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAiResponseDto {

	@JsonProperty("id")
	private String id;
	@JsonProperty("object")
	private String object;
	@JsonProperty("created")
	private Long created;
	@JsonProperty("model")
	private String model;
	@JsonProperty("choices")
	private List<Choice> choices;
	@JsonProperty("usage")
	private TokenUsage usage;

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	static class Choice {

		@JsonProperty("index")
		private Long index;
		@JsonProperty("message")
		private AiMessage message;
		@JsonProperty("finish_reason")
		private String finishReason;

	}

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	static class TokenUsage {

		@JsonProperty("prompt_tokens")
		private Long prompt;
		@JsonProperty("completion_tokens")
		private Long completion;
		@JsonProperty("total_tokens")
		private Long total;

	}
}
