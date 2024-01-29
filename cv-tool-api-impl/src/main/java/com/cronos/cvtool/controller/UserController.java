package com.cronos.cvtool.controller;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.user.UserDto;
import com.cronos.cvtool.exception.MyResourceNotFoundException;
import com.cronos.cvtool.service.UserService;
import com.cronos.cvtool.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@Tag(name = "User", description = "The user API")
@RestController
@RequestMapping("/user")
public class UserController {

	private UserService service;

	private PasswordEncoder encoder;
	
	public UserController(UserService service, PasswordEncoder encoder) {
		this.service = service;
		this.encoder = encoder;
	}

	@Operation(summary = "Get all users")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Retrieved all the users",
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
		),
		@ApiResponse(responseCode = "204", description = "No user found", content = @Content)
	})
	@GetMapping
	public ResponseEntity<ResponseDto> findAll() {
		log.info("GET - Find all users");
		List<UserDto> users = service.findAll();

		if(CollectionUtils.isEmpty(users)) {
			return Response.noContent();
		}

		return Response.ok(users);
	}

	@Operation(summary = "Get user by ID")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Found the user",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
		),
		@ApiResponse(responseCode = "204", description = "User not found", content = @Content),
		@ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content)
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable("id") String id) {
		log.info("GET - Find user with ID {}", id);
		if(StringUtils.isNumeric(id)) {
			try {
				UserDto user = service.findById(Long.parseLong(id));
				return Response.ok(user);
			}
			catch(MyResourceNotFoundException e) {
				log.warn("User not found");
				return Response.noContent();
			}
		}
		else {
			log.warn("Parameter id should be numeric");
			return Response.badRequest("Parameter id should be numeric");
		}
	}

	@Operation(summary = "Create a new user")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Created the user",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error creating the user", content = @Content),
		@ApiResponse(responseCode = "403", description = "Email already in use", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseDto> create(@RequestBody UserDto user) {
		try {
			log.info("POST - Create new user");

			//Encode user password
			String encodedPassword = encoder.encode(user.getPass());
			user.setPass(encodedPassword);

			// Save user
			service.create(user);
			return Response.created("A new user was created");
		}
		catch(DataIntegrityViolationException e) {
			log.error("Email already in use", e);
			return Response.forbiden("Email already in use");
		}
		catch(Exception e) {
			log.error("Error while creating new user", e);
			return Response.badRequest("Error while creating new user");
		}
	}

	@Operation(summary = "Update an existing user")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Updated the user",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error updating the user", content = @Content)
	})
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody UserDto user) {
		try {
			log.info("PUT - Update user with ID {}", id);
			user.setId(id);
			service.update(user);
			return Response.ok("The user was successfuly updated");
		}
		catch(Exception e) {
			log.error("Error while updating the user", e);
			return Response.badRequest("Error while updating the user");
		}
	}

	@Operation(summary = "Delete an existing user")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "Deleted the user",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
		),
		@ApiResponse(responseCode = "400", description = "Error deleting the user", content = @Content)
	})
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
		try {
			log.info("DELETE - Delete user with ID {}", id);
			service.deleteById(id);
			return Response.ok("The user was successfuly deleted");
		}
		catch(Exception e) {
			log.error("Error while deleting the user", e);
			return Response.badRequest("Error while deleting the user");
		}
	}

	@Operation(summary = "Login a user")
	@ApiResponses(value = {
		@ApiResponse(
			responseCode = "200", description = "User successfuly logged in",
			content = { @Content( mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }
		),
		@ApiResponse(responseCode = "401", description = "User or password incorrect", content = @Content)
	})
	@PostMapping(value = "/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseDto> login(@RequestBody UserDto user) {
		try {
			log.info("POST - Login user {}", user.getName());

			if(service.login(user)) {
				return Response.ok("User successfuly logged in");
			}
			else {
				return Response.unauthorized("User or password incorrect");
			}
		}
		catch(Exception e) {
			log.error("Error while trying to login user", e);
			return Response.badRequest("Error while deleting the user");
		}
	}

}
