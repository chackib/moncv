package com.cronos.cvtool.repository;

import com.cronos.cvtool.entity.candidate.CandidateLanguage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateLanguageRepository extends CrudRepository<CandidateLanguage, Long> {

    List<CandidateLanguage> findByCandidateId(Long candidateId);

    @Query("SELECT e FROM CandidateLanguage e WHERE e.candidate.id in :candidateIds")
    List<CandidateLanguage> findByCandidateIds(@Param("candidateIds") List<Long> candidateIds);
}
