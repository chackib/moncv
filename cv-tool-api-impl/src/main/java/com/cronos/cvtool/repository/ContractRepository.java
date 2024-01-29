package com.cronos.cvtool.repository;

import com.cronos.cvtool.entity.candidate.Contract;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContractRepository extends JpaRepository<Contract, Long> {

}
