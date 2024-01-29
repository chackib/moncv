package com.cronos.cvtool.dto.user;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonRootName(value = "roles")
public class RoleDto {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("permissions")
    Set<PermissionDto> permissions;

}
