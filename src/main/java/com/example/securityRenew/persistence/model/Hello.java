package com.example.securityRenew.persistence.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tb_hello")
@SequenceGenerator(
        name = "hno_seq_generator",
        sequenceName = "hno_seq",
        initialValue = 1, allocationSize = 1)

public class Hello {

    @Id
    @Column(name = "hno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hno_seq_generator")
    private Long hno;

    @Column(name="hello_writer")
    private String hello_writer;

    @Column(name="hello_greeting")
    private String hello_greeting;

    @Column(name="hello_writeday")
    private String hello_writeday;

    @Column(name="hello_like")
    private int hello_like;

    @Builder
    public Hello(Long hno, String hello_writer, String hello_greeting, String hello_writeday, int hello_like) {
        this.hno = hno;
        this.hello_writer = hello_writer;
        this.hello_greeting = hello_greeting;
        this.hello_writeday = hello_writeday;
        this.hello_like = hello_like;
    }
}

/*

-- TB_HELLO
CREATE TABLE tb_hello (
	hno number(9) NOT NULL,
	hello_writer varchar(255) NULL,
	hello_greeting varchar(255) NULL,
    hello_like number(9),
    hello_writeday varchar(60)
);


create SEQUENCE hno_seq;
commit;


 */