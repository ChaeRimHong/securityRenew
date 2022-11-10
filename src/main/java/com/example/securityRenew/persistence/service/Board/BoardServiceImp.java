package com.example.securityRenew.persistence.service.Board;

import com.example.securityRenew.persistence.dao.BoardRepository;
import com.example.securityRenew.persistence.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImp implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    public Page<Board> listpage(int page) {
        return boardRepository.findAll(PageRequest.of(page,20, Sort.by(Sort.Direction.DESC,"bno")));
    }

    @Override
    public List<Board> out() {
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "bno"));
    }

    @Override
    public void up_readcnt(Long bno) {
        boardRepository.readcnt(bno);
    }

    @Override
    public Board detail(Long bno) {
        return boardRepository.findById(bno).orElse(null);
    }
}
