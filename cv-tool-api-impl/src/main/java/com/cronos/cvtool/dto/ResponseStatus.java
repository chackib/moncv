package com.cronos.cvtool.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseStatus {

	private int code;
	private String message;

}
