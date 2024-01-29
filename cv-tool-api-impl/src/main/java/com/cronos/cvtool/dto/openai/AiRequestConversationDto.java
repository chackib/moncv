package com.cronos.cvtool.dto.openai;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AiRequestConversationDto {

	@JsonProperty("messages")
	private List<AiMessage> messages;

}
