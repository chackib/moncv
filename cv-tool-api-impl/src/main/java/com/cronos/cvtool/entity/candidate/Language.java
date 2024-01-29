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
@Table(name = "LANGUAGE")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LANGUAGE_ID", nullable = false)
	private Long id;

	@Column(name = "CODE", length = 3, nullable = false)
	private String code;

	@Column(name = "NAME", length = 30, nullable = false)
	private String name;

}
