package com.cronos.cvtool.service;

import com.cronos.cvtool.dto.candidate.ContractDto;
import com.cronos.cvtool.dto.candidate.CreateContractDto;
import com.cronos.cvtool.entity.candidate.Candidate;
import com.cronos.cvtool.entity.candidate.Contract;
import com.cronos.cvtool.enums.ContractStatus;
import com.cronos.cvtool.mapper.ContractMapper;
import com.cronos.cvtool.repository.CandidateRepository;
import com.cronos.cvtool.repository.ContractRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService{

    private final ContractRepository contractRepository;
    private final CandidateRepository candidateRepository;
    private final ContractMapper contractMapper = Mappers.getMapper(ContractMapper.class);

    public ContractServiceImpl(ContractRepository contractRepository, CandidateRepository candidateRepository) {
        this.contractRepository = contractRepository;
        this.candidateRepository = candidateRepository;
    }

    /**
     * Retrieves a paginated list of contracts based on the specified page, size, and sorting criteria.
     *
     * @param page   The page number.
     * @param size   The number of items per page.
     * @param sortBy The field to sort the results by.
     * @return A paginated list of ContractDto objects.
     */
    @Override
    public Page<ContractDto> getAllContracts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Contract> contractsPage = contractRepository.findAll(pageable);
        return contractsPage.map(contractMapper::entityToDto);
    }

    /**
     * Creates a new contract based on the provided CandidateDto.
     *
     * @param createContractDto The data transfer object containing contract information.
     * @return The created createContractDto representing the new contract.
     */
    @Override
    public CreateContractDto createContract(CreateContractDto createContractDto) {
        Contract contract = new Contract();
        contract.setContractName(createContractDto.getContractName());
        contract.setOverlapping(createContractDto.getOverlapping());
        contract.setStatus(ContractStatus.ONGOING);
        Set<Candidate> profiles = new HashSet<>(candidateRepository.findAllById(createContractDto.getProfiles()));
        contract.setProfiles(profiles);

        contractRepository.save(contract);
        return contractMapper.entityToCreateContractDto(contract);
    }

    /**
     * Deletes a contract based on the provided ID.
     *
     * @param id The ID of the contract to delete.
     */
    @Override
    public void deleteContract(Long id){
        contractRepository.deleteById(id);
    }

}
