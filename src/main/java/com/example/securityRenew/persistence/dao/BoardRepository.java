package com.example.securityRenew.persistence.dao;

import com.example.securityRenew.persistence.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    List<Board> findAll();



    @Transactional
    @Modifying
    @Query(value = "update tb_board set readcnt = readcnt +1 where tb_board.bno = :bno", nativeQuery = true )
    int readcnt(@Param("bno") Long bno);


    Optional<Object> findByBno(Long bno);
}
