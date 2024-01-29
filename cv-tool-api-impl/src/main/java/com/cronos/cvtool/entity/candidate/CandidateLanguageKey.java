package com.cronos.cvtool.entity.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CandidateLanguageKey implements Serializable {

    private static final long serialVersionUID = 6739157819462787407L;

    @Column(name = "CANDIDATE_ID")
    Long candidateId;

    @Column(name = "LANGUAGE_ID")
    Long languageId;

    // Default constructor
    public CandidateLanguageKey() {
    }

    // Add a constructor that initializes the key
    public CandidateLanguageKey(Long candidateId, Long languageId) {
        this.candidateId = candidateId;
        this.languageId = languageId;
    }

}
