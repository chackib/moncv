package com.cronos.cvtool.dto.candidate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class ProfileDto {
    private String firstName;
    private String lastName;
    private String profileName;
    private String statusCode;
    private LocalDateTime lastUpdate;
    private List<String> contractCode;
}
