package com.example.securityRenew.persistence.dao;

import com.example.securityRenew.persistence.model.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HelloRepository extends JpaRepository<Hello, Long> {


    Optional<Object> findByHno(Long hno);
}
