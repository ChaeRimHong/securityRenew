package com.example.securityRenew.persistence.dao;

import com.example.securityRenew.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SystemRepository extends JpaRepository<User,String> {

    @Override
    List<User> findAll();

    Optional<User> findById(String id);

}
