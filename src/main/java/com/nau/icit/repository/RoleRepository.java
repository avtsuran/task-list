package com.nau.icit.repository;

import com.nau.icit.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
