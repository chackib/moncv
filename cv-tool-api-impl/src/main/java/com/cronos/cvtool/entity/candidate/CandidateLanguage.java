package com.cronos.cvtool.entity.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CANDIDATE_LANGUAGE")
public class CandidateLanguage {

	@EmbeddedId
	CandidateLanguageKey id;

	@ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;

	@ManyToOne
    @MapsId("languageId")
    @JoinColumn(name = "LANGUAGE_ID")
	private Language language;

	@Column(name = "IS_MOTHER_TONGUE", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isMotherTongue;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SPOKEN_LANG_LEVEL_ID", referencedColumnName = "LANGUAGE_LEVEL_ID")
	private LanguageLevel spokenLevel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WRITTEN_LANG_LEVEL_ID", referencedColumnName = "LANGUAGE_LEVEL_ID")
	private LanguageLevel writtenLevel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UNDERSTOOD_LANG_LEVEL_ID", referencedColumnName = "LANGUAGE_LEVEL_ID")
	private LanguageLevel understoodLevel;
}
