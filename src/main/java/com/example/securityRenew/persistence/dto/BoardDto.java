package com.example.securityRenew.persistence.dto;

import com.example.securityRenew.persistence.model.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {


    private Long bno;

    private String btitle;

    private String bwriter;

    private LocalDate bwriteday;

    private String bcategory;

    private String bcontent;

    private int readcnt;

    private String storefilename;

    private String uploadfilename;

    private Set<Long> roles;

    public Board toEntity() {
        return Board.builder().bno(bno).btitle(btitle).bwriter(bwriter).bcategory(bcategory).bwriteday(bwriteday).bcontent(bcontent).readcnt(readcnt).storefilename(storefilename).uploadfilename(uploadfilename).build();
    }
}