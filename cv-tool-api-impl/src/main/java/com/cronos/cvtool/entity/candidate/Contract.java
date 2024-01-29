package com.cronos.cvtool.entity.candidate;

import com.cronos.cvtool.entity.Audit;
import com.cronos.cvtool.enums.ContractStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "CONTRACT")
public class Contract extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contractName;

    @Column(name = "contract_code")
    private String contractCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_status")
    private ContractStatus status;

    @Column(name = "overlapping")
    private Boolean overlapping;

    @Column(name = "started_date")
    private LocalDateTime startedDateTime;

    @Column(name = "finished_date")
    private LocalDateTime finishedDateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "candidate_contract",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    private Set<Candidate> profiles = new HashSet<>();
}
