package com.cronos.cvtool.service;

import com.cronos.cvtool.dto.candidate.ContractDto;
import com.cronos.cvtool.dto.candidate.CreateContractDto;
import org.springframework.data.domain.Page;

public interface ContractService {
    Page<ContractDto> getAllContracts(int page, int size, String sortBy);

    CreateContractDto createContract(CreateContractDto createContractDto);

    void deleteContract(Long id);
}
