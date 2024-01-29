package com.cronos.cvtool.entity.candidate;
import com.cronos.cvtool.entity.Audit;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "WORK_EXPERIENCE")
public class WorkExperience extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WORK_EXPERIENCE_ID", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CANDIDATE_ID", referencedColumnName = "CANDIDATE_ID", nullable = false)
    private Candidate candidate;
    @Column(name = "START_DATE", nullable = false)
    private LocalDate startDate;
    @Column(name = "END_DATE", nullable = false)
    private LocalDate endDate;
    @Column(name = "IS_ON_GOING")
    private Boolean isOnGoing;
    @Column(name = "POSITION_HELD", nullable = false)
    private String positionHeld;
    @Column(name = "PROJECT_NAME", nullable = false)
    private String projectName;
    @Column(name = "PROJECT_DESCRIPTION", nullable = false)
    private String projectDescription;
    @Column(name = "RESPONSABILITIES", nullable = false)
    private String responsibilities;
    @Column(name = "SUMMARY", nullable = false)
    private String summary;
    @Column(name = "TEAM_SIZE", nullable = false)
    private Integer teamSize;
    @Column(name = "EMPLOYER", nullable = false)
    private String employer;
    @Column(name = "EMPLOYER_ADDRESS", nullable = false)
    private String employerAddress;
    @Column(name = "CLIENT", nullable = false)
    private String client;
    @Column(name = "EC_EXPERIENCE_FLAG", nullable = false)
    private Boolean ecExperienceFlag;
    @Column(name = "MODE", nullable = false)
    private String mode;
    @Column(name = "PART_TIME_RATIO", nullable = false)
    private Double partTimeRatio;
    @Column(name = "IS_OVERLAPS", nullable = false)
    private Boolean isOverlaps;
    @Column(name = "CREATED_AT",insertable=false, updatable=false)
    private LocalDate createdAtt;
    @Column(name = "MODIFIED_AT", columnDefinition = "TIMESTAMP", insertable=false, updatable=false)
    private LocalDate modifiedAtt;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "USEFUL_LINKS", joinColumns = @JoinColumn(name = "WORK_EXPERIENCE_ID"))
    @Column(name = "USEFUL_LINKS")
    private Set<String> usefulLinks;
}

