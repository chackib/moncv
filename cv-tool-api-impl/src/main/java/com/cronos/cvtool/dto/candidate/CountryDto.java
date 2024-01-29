package com.cronos.cvtool.dto.candidate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CountryDto {

	@Schema(description = "The alpha-2 country code")
	private String code;
	@Schema(description = "The country name")
	private String name;

}
