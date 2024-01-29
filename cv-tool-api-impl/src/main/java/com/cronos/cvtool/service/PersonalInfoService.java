package com.cronos.cvtool.service;

import com.cronos.cvtool.dto.candidate.PersonalInfoDto;

public interface PersonalInfoService {
	
	PersonalInfoDto getPersonalInfo(Long idCandidate);

	PersonalInfoDto updatePersonalInfo(Long idCandiate, PersonalInfoDto personalInfoDto);

}
