package com.cronos.cvtool.entity.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "LANGUAGE_LEVEL")
public class LanguageLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LANGUAGE_LEVEL_ID", nullable = false)
	private Long id;

	@Column(name = "CODE", length = 2, nullable = false)
	private String code;

	@Column(name = "NAME", length = 20, nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", length = 500, nullable = false)
	private String description;

}
