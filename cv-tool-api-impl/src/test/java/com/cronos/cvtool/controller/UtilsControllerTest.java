package com.cronos.cvtool.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cronos.cvtool.dto.candidate.CountryDto;
import com.cronos.cvtool.dto.candidate.LanguageDto;
import com.cronos.cvtool.dto.candidate.LanguageLevelDto;
import com.cronos.cvtool.service.UtilsService;

@WebMvcTest(UtilsController.class)
class UtilsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UtilsService utilsService;


	@Test
	void testGetCountries() throws Exception {
		List<CountryDto> countries = new ArrayList<>();
		countries.add(CountryDto.builder().code("PT").name("Portugal").build());
		countries.add(CountryDto.builder().code("FR").name("France").build());

		when(utilsService.getCountries()).thenReturn(countries);

		mockMvc
			.perform(
				get("/utils/country")
				.with(user("user1").password("user1pass"))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.status.code", is(200)))
			.andExpect(jsonPath("$.status.message", is("OK")))
			.andExpect(jsonPath("$.data", hasSize(2)))
			.andExpect(jsonPath("$.data[0].code", is("PT")))
			.andExpect(jsonPath("$.data[0].name", is("Portugal")))
			.andExpect(jsonPath("$.data[1].code", is("FR")))
			.andExpect(jsonPath("$.data[1].name", is("France")));
	}

	@Test
	void testGetCountriesEmpty() throws Exception {
		List<CountryDto> countries = new ArrayList<>();

		when(utilsService.getCountries()).thenReturn(countries);

		mockMvc
			.perform(
				get("/utils/country")
				.with(user("user1").password("user1pass"))
			)
			.andExpect(status().isNoContent());
	}

	@Test
	void testGetLanguages() throws Exception {
		List<LanguageDto> languages = new ArrayList<>();
		languages.add(LanguageDto.builder().code("por").name("Portuguese").build());
		languages.add(LanguageDto.builder().code("fre").name("French").build());

		when(utilsService.getLanguages()).thenReturn(languages);

		mockMvc
			.perform(
				get("/utils/language")
				.with(user("user1").password("user1pass"))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.status.code", is(200)))
			.andExpect(jsonPath("$.status.message", is("OK")))
			.andExpect(jsonPath("$.data", hasSize(2)))
			.andExpect(jsonPath("$.data[0].code", is("por")))
			.andExpect(jsonPath("$.data[0].name", is("Portuguese")))
			.andExpect(jsonPath("$.data[1].code", is("fre")))
			.andExpect(jsonPath("$.data[1].name", is("French")));
	}

	@Test
	void testGetLanguagesEmpty() throws Exception {
		List<LanguageDto> languages = new ArrayList<>();

		when(utilsService.getLanguages()).thenReturn(languages);

		mockMvc
			.perform(
				get("/utils/language")
				.with(user("user1").password("user1pass"))
			)
			.andExpect(status().isNoContent());
	}

	@Test
	void testGetLanguageLevels() throws Exception {
		List<LanguageLevelDto> languageLevels = new ArrayList<>();
		languageLevels.add(LanguageLevelDto.builder().code("A1").name("Beginner").description("Can understand and use familiar everyday expressions and very basic phrases aimed at the satisfaction of needs of a concrete type. Can introduce themselves and others and can ask and answer questions about personal details such as where someone lives, people they know and things they have. Can interact in a simple way provided the other person talks slowly and clearly and is prepared to help.").build());
		languageLevels.add(LanguageLevelDto.builder().code("A2").name("Elementary").description("Can understand sentences and frequently used expressions related to areas of most immediate relevance (e.g. very basic personal and family information, shopping, local geography, employment). Can communicate in simple and routine tasks requiring a simple and direct exchange of information on familiar and routine matters. Can describe in simple terms aspects of their background, immediate environment and matters in areas of immediate need.").build());

		when(utilsService.getLanguageLevels()).thenReturn(languageLevels);

		mockMvc
			.perform(
				get("/utils/languagelevel")
				.with(user("user1").password("user1pass"))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.status.code", is(200)))
			.andExpect(jsonPath("$.status.message", is("OK")))
			.andExpect(jsonPath("$.data", hasSize(2)))
			.andExpect(jsonPath("$.data[0].code", is("A1")))
			.andExpect(jsonPath("$.data[0].name", is("Beginner")))
			.andExpect(jsonPath("$.data[0].description", is("Can understand and use familiar everyday expressions and very basic phrases aimed at the satisfaction of needs of a concrete type. Can introduce themselves and others and can ask and answer questions about personal details such as where someone lives, people they know and things they have. Can interact in a simple way provided the other person talks slowly and clearly and is prepared to help.")))
			.andExpect(jsonPath("$.data[1].code", is("A2")))
			.andExpect(jsonPath("$.data[1].name", is("Elementary")))
			.andExpect(jsonPath("$.data[1].description", is("Can understand sentences and frequently used expressions related to areas of most immediate relevance (e.g. very basic personal and family information, shopping, local geography, employment). Can communicate in simple and routine tasks requiring a simple and direct exchange of information on familiar and routine matters. Can describe in simple terms aspects of their background, immediate environment and matters in areas of immediate need.")));
	}

	@Test
	void testGetLanguageLevelsEmpty() throws Exception {
		List<LanguageLevelDto> languageLevels = new ArrayList<>();

		when(utilsService.getLanguageLevels()).thenReturn(languageLevels);

		mockMvc
			.perform(
				get("/utils/languagelevel")
				.with(user("user1").password("user1pass"))
			)
			.andExpect(status().isNoContent());
	}

}
