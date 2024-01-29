package com.cronos.cvtool.mapper;

import com.cronos.cvtool.dto.candidate.ContractDto;
import com.cronos.cvtool.dto.candidate.CreateContractDto;
import com.cronos.cvtool.entity.candidate.Candidate;
import com.cronos.cvtool.entity.candidate.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper
public interface ContractMapper {

    @Mapping(source = "contractName", target = "contractName")
    @Mapping(source = "contractCode", target = "contractCode")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "overlapping", target = "overlapping")
    @Mapping(source = "profiles", target = "profiles", qualifiedByName = "candidatesToNumberOfProfiles")
    ContractDto entityToDto(Contract profiles);

    @Named("candidatesToNumberOfProfiles")
    default Integer candidatesToNumberOfProfiles(Set<Candidate> profiles) {
        return profiles.size();
    }


    @Mapping(source = "contractName", target = "contractName")
    @Mapping(source = "overlapping", target = "overlapping")
    @Mapping(source = "profiles", target = "profiles", qualifiedByName = "candidatesToIdsOfProfiles")
    CreateContractDto entityToCreateContractDto(Contract profiles);

    @Named("candidatesToIdsOfProfiles")
    default List<Long> candidatesToIdsOfProfiles(Set<Candidate> profiles) {
        return profiles.stream().map(Candidate::getId).toList();
    }
}
