package com.cronos.cvtool.repository;

import com.cronos.cvtool.entity.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
