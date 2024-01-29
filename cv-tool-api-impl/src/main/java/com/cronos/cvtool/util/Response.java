package com.cronos.cvtool.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.ResponseStatus;

public class Response {

	private Response() {
		throw new IllegalStateException("Utility class");
	}

	public static <T> ResponseEntity<ResponseDto> ok(final T resource) {
		return Response.ok(resource, HttpStatus.OK.getReasonPhrase());
	}

	public static <T> ResponseEntity<ResponseDto> ok(final String message) {
		return Response.ok(null, message);
	}

	public static <T> ResponseEntity<ResponseDto> ok(final T resource, String message) {
		ResponseDto response = ResponseDto.builder()
		.status(
			ResponseStatus.builder()
				.code(HttpStatus.OK.value())
				.message(message)
				.build()
		)
		.data(resource)
		.build();

		return ResponseEntity.ok(response);
	}

	public static ResponseEntity<ResponseDto> created(String message) {
		ResponseDto response = ResponseDto.builder()
			.status(
				ResponseStatus.builder()
					.code(HttpStatus.CREATED.value())
					.message(message)
					.build()
			)
			.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	public static ResponseEntity<ResponseDto> noContent() {
		return ResponseEntity.noContent().build();
	}

	public static ResponseEntity<ResponseDto> badRequest(String message) {
		ResponseDto response = ResponseDto.builder()
			.status(
				ResponseStatus.builder()
					.code(HttpStatus.BAD_REQUEST.value())
					.message(message)
					.build()
			)
			.build();

		return ResponseEntity.badRequest().body(response);
	}

	public static ResponseEntity<ResponseDto> unauthorized(String message) {
		ResponseDto response = ResponseDto.builder()
			.status(
				ResponseStatus.builder()
					.code(HttpStatus.UNAUTHORIZED.value())
					.message(message)
					.build()
			)
			.build();

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}


	public static ResponseEntity<ResponseDto> forbiden(String message) {
		ResponseDto response = ResponseDto.builder()
			.status(
				ResponseStatus.builder()
					.code(HttpStatus.FORBIDDEN.value())
					.message(message)
					.build()
			)
			.build();

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	}
}
