package com.cronos.cvtool.entity.candidate;

import com.cronos.cvtool.entity.Audit;
import com.cronos.cvtool.entity.user.User;
import com.cronos.cvtool.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "CANDIDATE")
@EntityListeners(CandidateEntityListener.class)
public class Candidate extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CANDIDATE_ID", nullable = false)
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PHONE", nullable = false)
	private String phone;

	@Column(name = "BIRTHDATE", columnDefinition = "TIMESTAMP")
	protected LocalDate birthdate;

	@Column(name = "PROFILE_NAME", nullable = false)
	protected String profileName;

	@Column(name = "SUMMARY", nullable = false)
	private String summary;
	
	@Column(name = "IS_UNDER_WORK")
	private Boolean isUnderWork;

	@Column(name = "COMMENT")
	private String comment;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = false)
	private Status status;

    @Column(name = "REMOVED")
    private Boolean removed;

    @Column(name = "HIRE_DATE")
    private LocalDate hireDate;

	@Column(name = "LAST_UPDATE")
    private LocalDate lastUpdate;

    @Column(name = "COMPANY")
    private String company;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @Column(name = "PRIVATE_EMAIL")
    private String privateEmail;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	private User user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
	private Address address;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GENDER_ID", referencedColumnName = "GENDER_ID", nullable = false)
	private Gender gender;

	@ManyToMany(fetch = FetchType.EAGER)
    Set<Country> nationalities;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
      name = "CANDIDATE_LANGUAGE",
      joinColumns = @JoinColumn(name = "CANDIDATE_ID"),
      inverseJoinColumns = @JoinColumn(name = "LANGUAGE_ID"))
	List<Language> languages;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "candidate")
	List<WorkExperience> workExperiences;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID", referencedColumnName = "USER_ID")
	private User manager;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LAST_UPD_USER_ID", referencedColumnName = "USER_ID")
	private User lastUpdatedUser;

}
