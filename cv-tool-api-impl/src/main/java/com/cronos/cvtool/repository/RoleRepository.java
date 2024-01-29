package com.cronos.cvtool.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cronos.cvtool.entity.user.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	Optional<Role> findByCode(String code);

}
