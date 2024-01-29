package com.cronos.cvtool.dto.candidate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
public class PersonalInfoDto {

	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private LocalDate birthdate;
	private Boolean isUnderWork;
	private LocalDate hireDate;
    private String company;
    private String department;
    private String jobTitle;
    private Set<String> nationalities;
    private AddressDto address;
    private String privateEmail;

}
