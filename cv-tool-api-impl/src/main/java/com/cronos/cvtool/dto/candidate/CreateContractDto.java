package com.cronos.cvtool.dto.candidate;

import lombok.Data;
import java.util.List;

@Data
public class CreateContractDto {
    private String contractName;
    private Boolean overlapping;
    private List<Long> profiles;
}
