package com.cronos.cvtool.service;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import com.cronos.cvtool.dto.candidate.PersonalInfoDto;
import com.cronos.cvtool.entity.candidate.Candidate;
import com.cronos.cvtool.mapper.CandidateMapper;
import com.cronos.cvtool.repository.CandidateRepository;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

	private final CandidateRepository candidateRepository;
	private final CandidateService candidateService;
	private final AddressService addressService;
    private final CandidateMapper candidateMapper = Mappers.getMapper(CandidateMapper.class);


	public PersonalInfoServiceImpl(CandidateRepository candidateRepository, CandidateService candidateService,
								   AddressService addressService) {
		this.candidateRepository = candidateRepository;
		this.candidateService = candidateService;
		this.addressService = addressService;
	}


	/**
	 * Retrieves the personal information of a candidate based on the provided candidate ID.
	 *
	 * @param idCandidate The ID of the candidate to retrieve personal information for.
	 * @return The PersonalInfoDto representing the candidate's personal information.
	 * @throws IllegalArgumentException if no candidate is found with the given ID.
	 */
	@Override
	public PersonalInfoDto getPersonalInfo(Long idCandidate) {
		Candidate candidate = candidateRepository.findById(idCandidate)
				.orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + idCandidate));
		return candidateMapper.entityToPersonalInfoDto(candidate);
	}

	/**
	 * Updates the personal information of a candidate based on the provided candidate ID and PersonalInfoDto.
	 *
	 * @param idCandidate     The ID of the candidate to update personal information for.
	 * @param personalInfoDto The updated PersonalInfoDto containing the modified personal information.
	 * @return The PersonalInfoDto representing the updated personal information.
	 * @throws IllegalArgumentException if no candidate is found with the given ID.
	 */
	@Override
	public PersonalInfoDto updatePersonalInfo(Long idCandidate, PersonalInfoDto personalInfoDto) {
		// Retrieve the candidate by ID, throwing an exception if not found
		Candidate candidate = candidateRepository.findById(idCandidate)
				.orElseThrow(() -> new IllegalArgumentException("Candidate not found with id: " + idCandidate));

		// Update candidate information using the mapper
		candidateMapper.updateCandidateFromDto(personalInfoDto, candidate);

		// Update additional candidate information, e.g., nationalities
		candidate = candidateService.editCandidateInfos(candidate, personalInfoDto.getNationalities());

		// Save the candidate's address and update the candidate with the saved address
		candidate.setAddress(addressService.saveAddress(candidate.getAddress()));

		// Save the updated candidate
		candidateRepository.save(candidate);

		return personalInfoDto;
	}


}
