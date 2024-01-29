package com.cronos.cvtool.dto.candidate;

import com.cronos.cvtool.enums.ContractStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContractDto {
    private String contractName;
    private String contractCode;
    private ContractStatus status;
    private Boolean overlapping;
    private LocalDateTime startedDateTime;
    private LocalDateTime finishedDateTime;
    private Integer profiles;
}
