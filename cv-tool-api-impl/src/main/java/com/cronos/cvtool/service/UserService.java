package com.cronos.cvtool.service;

import java.util.List;

import com.cronos.cvtool.dto.user.UserDto;

public interface UserService {

	public List<UserDto> findAll();
	public UserDto findById(Long id);
	public void create(UserDto person);
	public void update(UserDto person);
	public void deleteById(Long id);
	public Boolean login(UserDto person);

}
