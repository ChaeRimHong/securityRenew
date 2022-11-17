package com.example.securityRenew.persistence.model;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.Date;

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

    @Column(name="bcategory")
    private String bcategory;

    @Column(name="bwriteday")
    private LocalDate bwriteday;

    @Column(name="bcontent")
    private String bcontent;

    @Column(name="readcnt")
    private int readcnt;

    @Column(name="storefilename")
    private String storefilename;

    @Column(name="uploadfilename")
    private String uploadfilename;

    @Builder
    public Board(Long bno, String btitle, String bwriter, String bcategory, LocalDate bwriteday, String bcontent, int readcnt,String storefilename, String uploadfilename) {
        this.bno = bno;
        this.btitle = btitle;
        this.bwriter = bwriter;
        this.bcategory = bcategory;
        this.bwriteday = bwriteday;
        this.bcontent = bcontent;
        this.readcnt = readcnt;
        this.storefilename = storefilename;
        this.uploadfilename = uploadfilename;

    }
}

/*

-- TB_BOARD
CREATE TABLE tb_board (
	bno number(9) NOT NULL,
	btitle varchar(255) NULL,
	bwriter varchar(255) NULL,
    bcontent varchar(900) NULL,
    readcnt number(9),
    bwriteday date,
    bcategory varchar(600) NULL,
    storefilename varchar(1800) NULL,
    uploadfilename varchar(1800) NULL
);


create SEQUENCE bno_seq;
commit;


 */