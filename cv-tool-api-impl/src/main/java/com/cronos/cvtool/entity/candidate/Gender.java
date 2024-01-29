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
@Table(name = "GENDER")
public class Gender {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GENDER_ID", nullable = false)
	private Long id;

	@Column(name = "CODE", length = 1, nullable = false, unique = true)
	private String code;

	@Column(name = "NAME", length = 10, nullable = false)
	private String name;

}
