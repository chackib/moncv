package com.cronos.cvtool.dto.openai;

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
public class AiMessage {

	@JsonProperty("role")
	private String role;
	@JsonProperty("content")
	private String content;

}
