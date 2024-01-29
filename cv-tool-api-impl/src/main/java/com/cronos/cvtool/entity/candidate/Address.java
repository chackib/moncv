package com.cronos.cvtool.entity.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDRESS_ID", nullable = false)
    private Long id;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "APARTMENT", nullable = true)
    private String apartment;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = true)
    private String state;

    @Column(name = "POSTAL_CODE", nullable = false)
    private String postalCode;

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID", nullable=true)
    private Country country;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Candidate candidate;

}
