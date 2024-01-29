package com.cronos.cvtool.dto.candidate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class CandidateDto {
	private Long id;
	private String summary;
	private Long userId;
	private String role;
	private String statusCode;
	private String genderCode;
	private Long idManager;
	private String managerName;
	private PersonalInfoDto personalInfo;
	private List<CandidateLanguageDto> candidateLanguages;
	private List<WorkExperienceDto> workExperiences;

//	private List<Education> education;
//	private List<Trainning> trainning;

}
