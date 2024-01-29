package com.cronos.cvtool.dto.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LanguageLevelDto {

	@Schema(description = "The CEFR code level")
	private String code;
	@Schema(description = "The level name")
	private String name;
	@Schema(description = "The level description")
	private String description;

}
