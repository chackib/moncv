package com.cronos.cvtool.dto.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CandidateLanguageDto {

	@Schema(description = "The alpha-3 language code")
	private String code;
	@Schema(description = "Is mother tongue")
	private Boolean isMotherTongue;
	@Schema(description = "Language written level code")
	private String languageWrittenLevelCode;
	@Schema(description = "Language spoken level code")
	private String languageSpokenLevelCode;
	@Schema(description = "Language understood level code")
	private String languageUnderstoodLevelCode;


}
