package com.cronos.cvtool.dto.candidate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
public class WorkExperienceDto {
    private Long id;
    private Long candidateId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String positionHeld;
    private String projectName;
    private Boolean isOnGoing;
    private String projectDescription;
    private String responsibilities;
    private String summary;
    private Integer teamSize;
    private String employer;
    private String employerAddress;
    private String client;
    private Boolean ecExperienceFlag;
    private String mode;
    private Double partTimeRatio;
    private Boolean isOverlaps;
    private LocalDate createdAtt;
    private LocalDate modifiedAtt;
    private Set<String> usefulLinks;
}