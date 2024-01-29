package com.cronos.cvtool.entity.user;

import java.time.LocalDate;
import java.util.HashSet;
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
@Table(name = "USER")
public class User extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;

	@Column(name = "PASS", nullable = false)
	private String pass;

	@Column(name = "IS_ACTIVE", nullable = false, columnDefinition = "boolean default true")
	private Boolean isActive;

	@Column(name = "PASS_CHANGE_DATE", columnDefinition = "DATE")
	private LocalDate passChangeDate;

	@Column(name = "IS_PASS_EXPIRED", nullable = false, columnDefinition = "boolean default false")
	private Boolean isPassExpired;

	@Column(name = "PASS_RESET_HASH")
	private String passResetHash;

	@ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

}
