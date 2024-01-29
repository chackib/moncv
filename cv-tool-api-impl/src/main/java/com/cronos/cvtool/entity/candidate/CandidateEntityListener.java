package com.cronos.cvtool.entity.candidate;

import jakarta.persistence.PreRemove;

public class CandidateEntityListener {
    @PreRemove
    public void onPreRemove(Candidate candidate) {
        candidate.getLanguages().clear();
    }
}
