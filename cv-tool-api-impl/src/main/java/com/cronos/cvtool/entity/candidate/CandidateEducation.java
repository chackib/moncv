package com.cronos.cvtool.entity.candidate;

import java.time.LocalDate;

import com.cronos.cvtool.entity.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "CANDIDATE_EDUCATION")
public class CandidateEducation extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CANDIDATE_EDUCATION_ID", nullable = false)
	private Long id;

	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;

	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "EDUCATION_DEGREE_ID")
	private EducationDegree educationDegree;

	@ManyToOne
    @MapsId("id")
    @JoinColumn(name = "COUNTRY_ID")
	private Country country;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "ORGANISATION_NAME", nullable = false)
	private String organizationName;

	@Column(name = "QUALIFICATION_AWARDED", nullable = false)
	private String qualificationAwarded;

	@Column(name = "MAIN_SUBJECT_COVERED", nullable = false)
	private String mainSubjectAwarded;

	@Column(name = "SPLIT_EDUCATION_FLAG", nullable = false)
	private Boolean isSplitEducation;

	@Column(name = "START_DATE", columnDefinition = "DATE", nullable = false)
	private LocalDate startDate;

	@Column(name = "END_DATE", columnDefinition = "DATE", nullable = true)
	private LocalDate endDate;
}
