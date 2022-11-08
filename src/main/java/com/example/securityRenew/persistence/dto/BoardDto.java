package com.example.securityRenew.persistence.dto;

import com.example.securityRenew.persistence.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {


    private Long bno;

    private String btitle;

    private String bwriter;

    private String bcontent;

    private String bfile;

    private int readcnt;

    private Set<Long> roles;

    public Board toEntity() {
        return Board.builder().bno(bno).btitle(btitle).bwriter(bwriter).bcontent(bcontent).bfile(bfile).readcnt(readcnt).build();
    }
}