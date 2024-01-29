package com.cronos.cvtool.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonRootName(value = "user")
public class UserDto {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
	@JsonProperty("pass")
	private String pass;
	@JsonProperty("isActive")
	private Boolean isActive;
	@JsonProperty("passChangeDate")
	private LocalDate passChangeDate;
	@JsonProperty("isPassExpired")
	private Boolean isPassExpired;
	@JsonProperty("passResetHash")
	private String passResetHash;
	@JsonProperty("createdAt")
	protected LocalDateTime createdAt;
	@JsonProperty("modifieddAt")
	protected LocalDateTime modifieddAt;
	@JsonProperty("roles")
    List<String> rolesCode;
	@JsonProperty("permissions")
    List<String> permissionsCode;

}
