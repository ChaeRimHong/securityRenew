package com.example.securityRenew.persistence.controller;

import com.example.securityRenew.persistence.dto.BoardDto;
import com.example.securityRenew.persistence.mapper.ServiceMapper;
import com.example.securityRenew.persistence.model.Board;
import com.example.securityRenew.persistence.service.Board.BoardService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    SqlSession sqlSession;


    @Autowired
    BoardService boardService;


    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/board")
    public String board(Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        Page<Board> boardPage = boardService.listpage(page);
        int totalPage = boardPage.getTotalPages();
        int nowpage = boardPage.getPageable().getPageNumber() + 1;//현재페이지
        model.addAttribute("nowpage", nowpage);
        model.addAttribute("list", boardPage.getContent());
        model.addAttribute("totalPage", totalPage);
        List<Board> list = boardService.out();
        return "board";
    }

    @GetMapping("/board_in")
    public String board_in() {
        return "board_in";
    }

    @RequestMapping(value = "/board_in_save", method = {RequestMethod.GET, RequestMethod.POST})
    public String board_in_save(BoardDto boardDto) {
        boardDto.setBwriteday(LocalDate.now());
        Board board = boardDto.toEntity();
        boardService.save(board);
        return "redirect:/board";
    }

    @GetMapping("board_detail_do")
    public String board_detail(@RequestParam("bno") Long bno, Model model) {
        boardService.up_readcnt(bno);
        Board board2 = boardService.detail(bno);
        if (board2 != null) {
            model.addAttribute("bno", board2.getBno());
            model.addAttribute("btitle", board2.getBtitle());
            model.addAttribute("bwriter", board2.getBwriter());
            model.addAttribute("bcategory", board2.getBcategory());
            model.addAttribute("bwriteday", board2.getBwriteday());
            model.addAttribute("bcontent", board2.getBcontent());
            model.addAttribute("bfile", board2.getBfile());
            model.addAttribute("readcnt", board2.getReadcnt());
        }
        return "board_detail";
    }


    @PostMapping("/search_do")
    public String search(Model mo, @RequestParam("keyword") String keyword) {
        ServiceMapper sm = sqlSession.getMapper(ServiceMapper.class);
        List<Board> list = sm.search(keyword);
        mo.addAttribute("list", list);
        return "search";
    }

    @GetMapping("/board_del")
    public String board_del(@RequestParam("bno") Long bno) {
        return "";
    }

    @GetMapping("/board_update")
    public String board_update(@RequestParam("bno") Long bno, Model mo) {
        System.out.println("bno number="+bno);
        Board board = boardService.boardUpdate(bno);

        mo.addAttribute("bno", board.getBno());
        mo.addAttribute("btitle", board.getBtitle());
        mo.addAttribute("bwriter", board.getBwriter());
        mo.addAttribute("bwriteday", board.getBwriteday());
        mo.addAttribute("bcategory", board.getBcategory());
        mo.addAttribute("bcontent", board.getBcontent());
        mo.addAttribute("bfile", board.getBfile());

        System.out.println("btitle ="+board.getBtitle());
        return "board_update";
    }

    @RequestMapping(value = "board_update_save", method = {RequestMethod.GET, RequestMethod.POST})
    public String board_update_save(BoardDto boardDto) {
        System.out.println("bno number="+boardDto.getBno());

        boardDto.setBwriteday(LocalDate.now());
        Board board = boardDto.toEntity();
        boardService.boardUpdateSave(board);
        return "redirect:/board";
    }

}