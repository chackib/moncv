package com.cronos.cvtool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cronos.cvtool.entity.candidate.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
