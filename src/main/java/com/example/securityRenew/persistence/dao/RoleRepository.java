package com.example.securityRenew.persistence.dao;

import com.example.securityRenew.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findQneById(Long id);
}