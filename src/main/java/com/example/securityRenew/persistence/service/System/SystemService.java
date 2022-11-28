package com.example.securityRenew.persistence.service.System;

import com.example.securityRenew.persistence.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SystemService {

    Page<User> listpage(int page);

    List<User> out();

    Optional<User> mypage(String now_id);
}
