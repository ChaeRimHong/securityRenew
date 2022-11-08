package com.example.securityRenew.persistence.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tb_board")
@SequenceGenerator(
        name = "bno_seq_generator",
        sequenceName = "bno_seq",
        initialValue = 1, allocationSize = 1)

public class Board {

    @Id
    @Column(name = "bno")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bno_seq_generator")
    private Long bno;

    @Column(name="btitle")
    private String btitle;

    @Column(name="bwriter")
    private String bwriter;

    @Column(name="bcontent")
    private String bcontent;

    @Column(name="bfile")
    private String bfile;


    @Column(name="readcnt")
    private int readcnt;


    @Builder
    public Board(Long bno, String btitle, String bwriter, String bcontent,String bfile, int readcnt) {
        this.bno = bno;
        this.btitle = btitle;
        this.bwriter = bwriter;
        this.bcontent = bcontent;
        this.bfile = bfile;
        this.readcnt = readcnt;
    }
}

/*
-- TB_BOARD
CREATE TABLE tb_board (
	bno number(9) NOT NULL,
	btitle varchar(255) NULL,
	bwriter varchar(255) NULL,
    bcontent varchar(900) NULL,
    bfile varchar(1800) NULL,
    readcnt number(9)
);

create SEQUENCE bno_seq;
commit;


 */