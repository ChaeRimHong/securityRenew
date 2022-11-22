package com.example.securityRenew.persistence.service.Hello;

import com.example.securityRenew.persistence.dao.HelloRepository;
import com.example.securityRenew.persistence.model.Board;
import com.example.securityRenew.persistence.model.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloServiceImp implements HelloService {
    @Autowired
    HelloRepository helloRepository;

    @Override
    public void save(Hello hello) {
        helloRepository.save(hello);
    }

    @Override
    public Page<Hello> listpage(int page) {
        return helloRepository.findAll(PageRequest.of(page,20, Sort.by(Sort.Direction.DESC,"hno")));
    }

    @Override
    public List<Hello> out() {
        return HelloRepository.findAll(Sort.by(Sort.Direction.DESC, "hno"));
    }
}
