package com.cronos.cvtool.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cronos.cvtool.dto.user.UserDto;
import com.cronos.cvtool.entity.user.Role;
import com.cronos.cvtool.entity.user.User;
import com.cronos.cvtool.exception.MyResourceNotFoundException;
import com.cronos.cvtool.mapper.UserMapper;
import com.cronos.cvtool.repository.RoleRepository;
import com.cronos.cvtool.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	private UserMapper mapper = Mappers.getMapper(UserMapper.class);

	@Override
	public List<UserDto> findAll() {
		return StreamSupport.stream(userRepository.findAll().spliterator(), false)
			.map(user -> mapper.entityToDto(user))
			.collect(Collectors.toList());
	}

	@Override
	public UserDto findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new MyResourceNotFoundException());
		return mapper.entityToDto(user);
	}

	@Override
	public void create(UserDto userDto) {
		// Get User object
		User user = mapper.dtoToEntity(userDto);

		// Add roles
		Set<Role> roles = userDto.getRolesCode().stream()
			.map(role -> roleRepository.findByCode(role).orElse(null))
			.collect(Collectors.toSet());
		user.getRoles().addAll(roles);

		// Save
		userRepository.save(user);
	}

	@Override
	public void update(UserDto userDto) {
		// Get user
		User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new MyResourceNotFoundException());

		// Set new values
		if(StringUtils.isNotBlank(userDto.getName())) {
			user.setName(userDto.getName());
		}
		if(StringUtils.isNotBlank(userDto.getEmail())) {
			user.setEmail(userDto.getEmail());
		}
		if(StringUtils.isNotBlank(userDto.getPass())) {
			user.setPass(userDto.getPass());
		}
		if(userDto.getIsActive() != null) {
			user.setIsActive(userDto.getIsActive());
		}
		if(userDto.getPassChangeDate() != null) {
			user.setPassChangeDate(userDto.getPassChangeDate());
		}
		if(userDto.getIsPassExpired() != null) {
			user.setIsPassExpired(userDto.getIsPassExpired());
		}
		if(StringUtils.isNotBlank(userDto.getPassResetHash())) {
			user.setPassResetHash(userDto.getPassResetHash());
		}

		userRepository.save(user);
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Boolean login(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail()).orElseThrow(() -> new MyResourceNotFoundException());

		return encoder.matches(userDto.getPass(), user.getPass());
	}
}
