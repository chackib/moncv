package com.cronos.cvtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cronos.cvtool.entity.candidate.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long>{
	Gender getGenderByCode(String code);
}
