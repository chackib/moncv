package com.cronos.cvtool.service;

import java.util.Set;

import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.dto.candidate.ProfileDto;
import com.cronos.cvtool.entity.candidate.Candidate;
import org.springframework.data.domain.Page;

public interface CandidateService {
	Page<CandidateDto> getAllCandidates(int page, int size, String sortBy);

	Page<ProfileDto> getAllProfiles(int page, int size, String sortBy);

	CandidateDto getCandidateById(Long id);
	CandidateDto createCandidate(CandidateDto candidateDTO);
	CandidateDto updateCandidate(Long id, CandidateDto candidateDTO);
    void deleteCandidate(Long id);

	Candidate editCandidateInfos(Candidate candidate, Set<String> nationalitiesCodes);
}
