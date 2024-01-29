package com.cronos.cvtool.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cronos.cvtool.entity.candidate.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
	Country getCountryByCode(String code);

}
