package com.cronos.cvtool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseDto {

	private ResponseStatus status;
	@JsonInclude(Include.NON_NULL)
	private Object data;

}
