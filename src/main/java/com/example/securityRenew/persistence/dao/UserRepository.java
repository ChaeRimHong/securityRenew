package com.example.securityRenew.persistence.dao;

import com.example.securityRenew.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findOneById(String id);
}