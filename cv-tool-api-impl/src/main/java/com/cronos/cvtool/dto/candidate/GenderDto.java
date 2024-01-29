package com.cronos.cvtool.dto.candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GenderDto {
	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
}
