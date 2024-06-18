package org.vvh.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vvh.springsecurity.entity.Role;
import org.vvh.springsecurity.enums.RoleEnum;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
