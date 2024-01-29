package com.cronos.cvtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cronos.cvtool.entity.candidate.LanguageLevel;

@Repository
public interface LanguageLevelRepository extends CrudRepository<LanguageLevel, Long> {

    LanguageLevel findByCode(String code);
}
