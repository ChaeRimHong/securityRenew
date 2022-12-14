package com.example.securityRenew.persistence.dto;

import com.example.securityRenew.persistence.model.Hello;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class HelloDto{

    private Long hno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private String hello_writer;

    private String hello_writeday;

    private String hello_greeting;

    private int hello_like;

    private Set<Long> roles;

    public Hello toEntity() {
        return Hello.builder().hno(hno).hello_writer(hello_writer).hello_greeting(hello_greeting).hello_writeday(hello_writeday).hello_like(hello_like).build();
    }
}