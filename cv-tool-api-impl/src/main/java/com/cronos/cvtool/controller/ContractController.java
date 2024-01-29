package com.cronos.cvtool.controller;

import com.cronos.cvtool.dto.ResponseDto;
import com.cronos.cvtool.dto.candidate.CandidateDto;
import com.cronos.cvtool.dto.candidate.ContractDto;
import com.cronos.cvtool.dto.candidate.CreateContractDto;
import com.cronos.cvtool.entity.candidate.Contract;
import com.cronos.cvtool.service.ContractService;
import com.cronos.cvtool.util.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@Tag(name = "Contract", description = "The contract API")
@RestController
@RequestMapping("/contract")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @Operation(summary = "Get contracts list")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Retrieved contracts list",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ContractDto.class)) }
            ),
            @ApiResponse(responseCode = "204", description = "No contract found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Page<ContractDto>> getAllContracts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "2") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {

        log.info("GET - Retrieving contracts list (Page: {}, Size: {}, SortBy: {})", page, size, sortBy);

        Page<ContractDto> contractsPage = contractService.getAllContracts(page, size, sortBy);

        if (contractsPage.isEmpty()) {
            log.info("GET - No contract found");
            return ResponseEntity.noContent().build();
        }

        log.info("GET - Contracts retrieved successfully");
        return ResponseEntity.ok(contractsPage);
    }

    @Operation(summary = "Add a new Contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contract created",
                    content = { @Content( mediaType = "application/json", schema = @Schema(implementation = Contract.class)) }
            ),
            @ApiResponse(responseCode = "400", description = "Error creating the Contract", content = @Content),
    })
    @PostMapping
    public ResponseEntity<ResponseDto> createContract(@RequestBody CreateContractDto createContractDto) {
        try {
            log.info("POST - Create new Contract");
            CreateContractDto createdContract = contractService.createContract(createContractDto);
            return Response.ok(createdContract);
        } catch(Exception e) {
            log.error("Error while creating new Contract", e);
            return Response.badRequest("Error while creating new Contract");
        }
    }

    @Operation(summary = "Delete an existing contract")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Deleted the contract",
                    content = { @Content( mediaType = "application/json", schema = @Schema(implementation = CandidateDto.class)) }
            ),
            @ApiResponse(responseCode = "400", description = "Error deleting the contract", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteContract(@PathVariable Long id) {
        try {
            log.info("DELETE - Delete contract with ID {}", id);
            contractService.deleteContract(id);
            return Response.ok("The contract was successfully deleted");
        } catch(Exception e) {
            log.error("Error while deleting the contract", e);
            return Response.badRequest("Error while deleting the contract");
        }
    }
}
