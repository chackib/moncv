package com.cronos.cvtool.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.cronos.cvtool.dto.user.UserDto;
import com.cronos.cvtool.entity.user.Role;
import com.cronos.cvtool.entity.user.User;

@Mapper
public interface UserMapper {

	@Mapping(target = "roles", ignore = true)
	User dtoToEntity(UserDto user);

	@Mapping(source = "roles", target = "rolesCode", qualifiedByName = "rolesToRolesCode")
	@Mapping(source = "roles", target = "permissionsCode", qualifiedByName = "rolesToPermissionsCode")
	UserDto entityToDto(User user);

	@Named("rolesToRolesCode")
	public static List<String> rolesToRolesCode(Set<Role> roles) {
		return roles.stream()
			.map(role -> role.getCode())
			.collect(Collectors.toList());
    }

	@Named("rolesToPermissionsCode")
	public static List<String> rolesToPermissionsCode(Set<Role> roles) {
		return roles.stream()
			.flatMap(role -> role.getPermissions().stream())
			.map(permission -> permission.getCode())
			.collect(Collectors.toList());
    }
}
