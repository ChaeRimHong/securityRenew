package com.example.securityRenew.persistence.service.Hello;

import com.example.securityRenew.persistence.model.Hello;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HelloService {

    public Hello save(Hello hello);

    Page<Hello> listpage(int page);

    List<Hello> out();
}
