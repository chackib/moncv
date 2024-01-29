package com.cronos.cvtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cronos.cvtool.entity.candidate.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long> {
	Language getLanguageByCode(String code);
}
