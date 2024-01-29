package com.cronos.cvtool.entity.user;

import com.cronos.cvtool.entity.Audit;

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
@Table(name = "PERMISSION")
public class Permission extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERMISSION_ID", nullable = false)
	private Long id;

	@Column(name = "CODE", nullable = false, unique = true)
	private String code;

	@Column(name = "NAME", nullable = false)
	private String name;

}
