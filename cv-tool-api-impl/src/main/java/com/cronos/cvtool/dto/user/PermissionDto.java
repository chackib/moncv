package com.cronos.cvtool.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonRootName(value = "permissions")
public class PermissionDto {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;

}
