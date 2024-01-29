package com.cronos.cvtool.controller;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.PersonalInfoDto;
import com.cronos.cvtool.service.PersonalInfoService;
import com.cronos.cvtool.util.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonalInfoControllerTest {

    @Mock
    private PersonalInfoService personalInfoService;

    @InjectMocks
    private PersonalInfoController personalInfoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPersonalInfo_shouldReturnPersonalInfoDto_whenCandidateExists() {
        // GIVEN
        Long candidateId = 1L;
        PersonalInfoDto personalInfoDto = PersonalInfoDto.builder().build();

        when(personalInfoService.getPersonalInfo(candidateId)).thenReturn(personalInfoDto);

        // WHEN
        ResponseEntity<ResponseDto> response = personalInfoController.getPersonalInfo(candidateId);

        // THEN
        assertEquals(Response.ok(personalInfoDto).getStatusCode(), response.getStatusCode());
        // assertEquals(Response.ok(personalInfoDto).getBody(), response.getBody());
        verify(personalInfoService, times(1)).getPersonalInfo(candidateId);
    }

    @Test
    void getPersonalInfo_shouldReturnNoContent_whenCandidateDoesNotExist() {
        // GIVEN
        Long candidateId = 1L;

        when(personalInfoService.getPersonalInfo(candidateId)).thenReturn(null);

        // WHEN
        ResponseEntity<ResponseDto> response = personalInfoController.getPersonalInfo(candidateId);

        // THEN
        assertEquals(Response.noContent().toString(), response.toString());
        verify(personalInfoService, times(1)).getPersonalInfo(candidateId);
    }

    @Test
    void updatePersonalInfo_shouldReturnUpdatedPersonalInfoDto_whenUpdateIsSuccessful() {
        // GIVEN
        Long candidateId = 1L;
        PersonalInfoDto personalInfoDto = PersonalInfoDto.builder().build();
        PersonalInfoDto updatedPersonalInfoDto = PersonalInfoDto.builder().build();

        when(personalInfoService.updatePersonalInfo(candidateId, personalInfoDto)).thenReturn(updatedPersonalInfoDto);

        // WHEN
        ResponseEntity<ResponseDto> response = personalInfoController.updatePersonalInfo(candidateId, personalInfoDto);

        // THEN
        assertEquals(updatedPersonalInfoDto, Objects.requireNonNull(response.getBody()).getData());
        verify(personalInfoService, times(1)).updatePersonalInfo(candidateId, personalInfoDto);
    }

    @Test
    void updatePersonalInfo_shouldReturnBadRequest_whenUpdateFails() {
        // GIVEN
        Long candidateId = 1L;
        PersonalInfoDto personalInfoDto = PersonalInfoDto.builder().build();

        when(personalInfoService.updatePersonalInfo(candidateId, personalInfoDto)).thenThrow(new RuntimeException());

        // WHEN
        ResponseEntity<ResponseDto> response = personalInfoController.updatePersonalInfo(candidateId, personalInfoDto);

        // THEN
        assertEquals(Response.badRequest("Error while updating the personal infos").getStatusCode(), response.getStatusCode());
        verify(personalInfoService, times(1)).updatePersonalInfo(candidateId, personalInfoDto);
    }
}
