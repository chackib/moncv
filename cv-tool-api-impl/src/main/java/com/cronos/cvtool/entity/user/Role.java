package com.cronos.cvtool.entity.user;

import java.util.Set;

import com.cronos.cvtool.entity.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Table(name = "ROLE")
public class Role extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID", nullable = false)
	private Long id;

	@Column(name = "CODE", nullable = false, unique = true)
	private String code;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
    Set<Permission> permissions;
}
