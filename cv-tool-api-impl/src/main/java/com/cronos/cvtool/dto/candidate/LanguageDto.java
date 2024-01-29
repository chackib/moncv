package com.cronos.cvtool.dto.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LanguageDto {

	@Schema(description = "The alpha-3 language code")
	private String code;
	@Schema(description = "The language name")
	private String name;

}
