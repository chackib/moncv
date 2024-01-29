package com.cronos.cvtool.mapper;

import com.cronos.cvtool.dto.candidate.AddressDto;
import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.dto.candidate.CandidateLanguageDto;
import com.cronos.cvtool.dto.candidate.ProfileDto;
import com.cronos.cvtool.dto.candidate.PersonalInfoDto;
import com.cronos.cvtool.dto.candidate.WorkExperienceDto;
import com.cronos.cvtool.entity.candidate.Address;
import com.cronos.cvtool.entity.candidate.Candidate;
import com.cronos.cvtool.entity.candidate.CandidateLanguage;
import com.cronos.cvtool.entity.candidate.Country;
import com.cronos.cvtool.entity.candidate.LanguageLevel;
import com.cronos.cvtool.entity.candidate.WorkExperience;
import com.cronos.cvtool.entity.user.User;
import com.cronos.cvtool.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cronos.cvtool.enums.Status.COMPLETE;
import static com.cronos.cvtool.enums.Status.READY_FOR_REVIEW;
import static com.cronos.cvtool.enums.Status.UNDER_WORK;

@Mapper
public interface CandidateMapper {

	@Mapping(source = "user.id", target = "id")
	@Mapping(source = "user.user.id", target = "userId")
	@Mapping(source = "user.status", target = "statusCode", qualifiedByName = "mapStatusToCode")
	@Mapping(target = "user.role", ignore = true)
	@Mapping(source = "user.firstName", target = "personalInfo.firstName")
	@Mapping(source = "user.lastName", target = "personalInfo.lastName")
	@Mapping(source = "user.email", target = "personalInfo.email")
	@Mapping(source = "user.phone", target = "personalInfo.phone")
	@Mapping(source = "user.birthdate", target = "personalInfo.birthdate")
	@Mapping(source = "user.isUnderWork", target = "personalInfo.isUnderWork")
	@Mapping(source = "user.hireDate", target = "personalInfo.hireDate")
	@Mapping(source = "user.company", target = "personalInfo.company")
	@Mapping(source = "user.department", target = "personalInfo.department")
	@Mapping(source = "user.jobTitle", target = "personalInfo.jobTitle")
	@Mapping(target = "personalInfo.address", qualifiedByName = "addressToAddressDto")
	@Mapping(target = "idManager", source = "user.manager", qualifiedByName = "managerToIdManager")
	@Mapping(target = "managerName", source = "user.manager", qualifiedByName = "managerToManagerName")
	@Mapping(source = "user.privateEmail", target = "personalInfo.privateEmail")
	@Mapping(source = "user.gender.code", target = "genderCode")
	@Mapping(source = "candidateLanguages",  target = "candidateLanguages", qualifiedByName = "setLanguagesToLanguages")
	@Mapping(target = "personalInfo.nationalities", qualifiedByName = "setCountriesToCountriesCodes")
	@Mapping(source = "user.workExperiences", target = "workExperiences", qualifiedByName = "workExperienceToWorkExperienceDto")
	CandidateDto entityToDto(Candidate user,  List<CandidateLanguage> candidateLanguages);

	@Named("setLanguagesToLanguages")
	default List<CandidateLanguageDto> setLanguagesToLanguages(List<CandidateLanguage> candidateLanguages) {
		return candidateLanguages.stream()
				.map(candidateLanguage -> CandidateLanguageDto.builder().code(candidateLanguage.getLanguage()
						.getCode())
						.isMotherTongue(candidateLanguage.getIsMotherTongue())
						.languageSpokenLevelCode(Optional.ofNullable(candidateLanguage.getSpokenLevel()).map(LanguageLevel::getCode).orElse(null))
						.languageWrittenLevelCode(Optional.ofNullable(candidateLanguage.getWrittenLevel()).map(LanguageLevel::getCode).orElse(null))
						.languageUnderstoodLevelCode(Optional.ofNullable(candidateLanguage.getUnderstoodLevel()).map(LanguageLevel::getCode).orElse(null))
						.build())
				.toList();
	}


	@Named("mapStatusToCode")
	default String mapStatusToCode(Status status) {
        return switch (status) {
            case READY_FOR_REVIEW -> "RFR";
            case COMPLETE -> "CMP";
            case UNDER_WORK -> "UW";
            default -> throw new IllegalArgumentException("Unsupported Status: " + status);
        };
	}

	@Named("mapCodeToStatus")
	default Status mapCodeToStatus(String statusCode) {
        return switch (statusCode) {
            case  "RFR" -> READY_FOR_REVIEW;
            case  "CMP" -> COMPLETE;
            case  "UW" -> UNDER_WORK;
            default -> throw new IllegalArgumentException("Unsupported Status: " + statusCode);
        };
	}

	@Named("setCountriesToCountriesCodes")
	default Set<String> setCountriesToCountriesCodes(Set<Country> countries) {
		return countries.stream()
				.map(Country::getCode)
				.collect(Collectors.toSet());
	}

	@Mapping(source = "personalInfo.firstName", target = "firstName")
	@Mapping(source = "personalInfo.lastName", target = "lastName")
	@Mapping(source = "personalInfo.email", target = "email")
	@Mapping(source = "personalInfo.phone", target = "phone")
	@Mapping(source = "personalInfo.birthdate", target = "birthdate")
	@Mapping(source = "personalInfo.isUnderWork", target = "isUnderWork")
	@Mapping(source = "personalInfo.hireDate", target = "hireDate")
	@Mapping(source = "personalInfo.company", target = "company")
	@Mapping(source = "personalInfo.department", target = "department")
	@Mapping(source = "personalInfo.jobTitle", target = "jobTitle")
	@Mapping(target = "manager", ignore = true)
	@Mapping(target = "address", source = "personalInfo.address", qualifiedByName = "addressDtoToAddress")
	@Mapping(source = "personalInfo.privateEmail", target = "privateEmail")
	@Mapping(target = "languages", ignore = true)
	@Mapping(target = "nationalities", ignore = true)
	@Mapping(target = "user", ignore = true)
	@Mapping(target = "status", source = "statusCode", qualifiedByName = "mapCodeToStatus")
	Candidate dtoToEntity(CandidateDto candidateDto);

	@Named("addressDtoToAddress")
	@Mapping(source = "country", target = "country.code")
	Address addressDtoToAddress(AddressDto addressDto);

	@Named("addressToAddressDto")
	@Mapping(source = "country.code", target = "country")
	AddressDto addressToAddressDto(Address address);

	@Named("workExperienceToWorkExperienceDto")
	@Mapping(source = "id", target = "id")
	@Mapping(source = "startDate", target = "startDate")
	@Mapping(source = "endDate", target = "endDate")
	@Mapping(source = "positionHeld", target = "positionHeld")
	@Mapping(source = "projectName", target = "projectName")
	@Mapping(source = "projectDescription", target = "projectDescription")
	@Mapping(source = "responsibilities", target = "responsibilities")
	@Mapping(source = "summary", target = "summary")
	@Mapping(source = "teamSize", target = "teamSize")
	@Mapping(source = "employer", target = "employer")
	@Mapping(source = "employerAddress", target = "employerAddress")
	@Mapping(source = "client", target = "client")
	@Mapping(source = "ecExperienceFlag", target = "ecExperienceFlag")
	@Mapping(source = "mode", target = "mode")
	@Mapping(source = "partTimeRatio", target = "partTimeRatio")
	@Mapping(source = "isOverlaps", target = "isOverlaps")
	@Mapping(source = "createdAtt", target = "createdAtt")
	@Mapping(source = "modifiedAtt", target = "modifiedAtt")
	@Mapping(source = "usefulLinks", target = "usefulLinks")
	@Mapping(source = "isOnGoing", target = "isOnGoing")
	WorkExperienceDto workExperienceToWorkExperienceDto(WorkExperience workExperience);

	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "phone", target = "phone")
	@Mapping(source = "birthdate", target = "birthdate")
	@Mapping(source = "isUnderWork", target = "isUnderWork")
	@Mapping(source = "hireDate", target = "hireDate")
	@Mapping(source = "company", target = "company")
	@Mapping(source = "department", target = "department")
	@Mapping(source = "jobTitle", target = "jobTitle")
	@Mapping(target = "address", qualifiedByName = "addressToAddressDto")
	@Mapping(source = "privateEmail", target = "privateEmail")
	@Mapping(target = "nationalities", qualifiedByName = "setCountriesToCountriesCodes")
	PersonalInfoDto entityToPersonalInfoDto(Candidate candidate);

	@Named("managerToIdManager")
	default Long managerToIdManager(User manager) {
		return Optional.ofNullable(manager).map(User::getId).orElse(null);
	}

	@Named("managerToManagerName")
	default String managerToManagerName(User manager) {
		return Optional.ofNullable(manager).map(User::getName).orElse(null);
	}


	@Mapping(source = "status", target = "statusCode", qualifiedByName = "mapStatusToCode")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastUpdate", target = "lastUpdate")
	ProfileDto candidateToCandidatesDto(Candidate candidate);


	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "phone", target = "phone")
	@Mapping(source = "birthdate", target = "birthdate")
	@Mapping(source = "isUnderWork", target = "isUnderWork")
	@Mapping(source = "hireDate", target = "hireDate")
	@Mapping(source = "company", target = "company")
	@Mapping(source = "department", target = "department")
	@Mapping(source = "jobTitle", target = "jobTitle")
	@Mapping(target = "address",  qualifiedByName = "addressDtoToAddress")
	@Mapping(source = "privateEmail", target = "privateEmail")
	@Mapping(target = "nationalities", ignore = true)
	void updateCandidateFromDto(PersonalInfoDto dto, @MappingTarget Candidate candidate);
}
