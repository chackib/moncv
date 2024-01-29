package com.cronos.cvtool.entity.candidate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "COUNTRY")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_ID", nullable = false)
	private Long id;

	@Column(name = "CODE", length = 2, nullable = false)
	private String code;

	@Column(name = "NAME", length = 60, nullable = false)
	private String name;

}
