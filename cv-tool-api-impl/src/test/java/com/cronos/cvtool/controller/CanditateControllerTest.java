package com.cronos.cvtool.controller;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.exception.MyResourceNotFoundException;
import com.cronos.cvtool.service.CandidateService;
import com.cronos.cvtool.util.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCandidates() {

        Page<CandidateDto> mockCandidates = mock(Page.class);
        when(candidateService.getAllCandidates(anyInt(), anyInt(), anyString())).thenReturn(mockCandidates);

        ResponseEntity<Page<CandidateDto>> response = candidateController.getAllCandidates(1, 10, "id");

        assertEquals(ResponseEntity.ok(mockCandidates), response);
        verify(candidateService, times(1)).getAllCandidates(1, 10, "id");
    }

    @Test
    void getCandidateById_ExistingCandidate() {
        Long existingCandidateId = 1L;
        CandidateDto mockCandidateDto = mock(CandidateDto.class);
        when(candidateService.getCandidateById(existingCandidateId)).thenReturn(mockCandidateDto);

        ResponseEntity<ResponseDto> response = candidateController.getCandidateById(existingCandidateId.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockCandidateDto, response.getBody().getData());
        verify(candidateService, times(1)).getCandidateById(existingCandidateId);
    }

    @Test
    void getCandidateById_NonExistingCandidate() {
        Long nonExistingCandidateId = 2L;
        when(candidateService.getCandidateById(nonExistingCandidateId))
                .thenThrow(new MyResourceNotFoundException("Candidate not found"));

        ResponseEntity<ResponseDto> response = candidateController.getCandidateById(nonExistingCandidateId.toString());

        assertEquals(Response.noContent(), response);
        verify(candidateService, times(1)).getCandidateById(nonExistingCandidateId);
    }
}
