package com.example.securityRenew.persistence.service.System;

import com.example.securityRenew.persistence.dao.SystemRepository;
import com.example.securityRenew.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemServiceImp implements SystemService{

    @Autowired
    SystemRepository systemRepository;

    @Override
    public Page<User> listpage(int page) {
        return systemRepository.findAll(PageRequest.of(page,20, Sort.by(Sort.Direction.DESC,"id")));
    }

    @Override
    public List<User> out() {
        return systemRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Optional<User> mypage(String now_id) {
        return systemRepository.findById(now_id);
    }


}
