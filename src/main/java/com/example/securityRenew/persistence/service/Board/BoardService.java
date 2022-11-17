package com.example.securityRenew.persistence.service.Board;

import com.example.securityRenew.persistence.model.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
    public void save(Board board);

    Page<Board> listpage(int page);

    List<Board> out();

    void up_readcnt(Long bno);

    Board detail(Long bno);

    Board boardUpdate(Long bno);

    void boardUpdateSave(Board board);

    void board_del(Long bno);
}
