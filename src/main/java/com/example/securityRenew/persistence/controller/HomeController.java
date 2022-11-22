package com.example.securityRenew.persistence.controller;

import com.example.securityRenew.persistence.dto.BoardDto;
import com.example.securityRenew.persistence.dto.HelloDto;
import com.example.securityRenew.persistence.mapper.ServiceMapper;
import com.example.securityRenew.persistence.model.Board;
import com.example.securityRenew.persistence.model.Hello;
import com.example.securityRenew.persistence.service.Board.BoardService;
import com.example.securityRenew.persistence.service.Hello.HelloService;
import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    SqlSession sqlSession;


    @Autowired
    BoardService boardService;

    @Autowired
    HelloService helloService;

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
    public String board_in_save(BoardDto boardDto, @RequestParam("file") MultipartFile mpf,Model mo) throws IOException {
        if(!mpf.isEmpty()) {
            String fullpath = "C:/springboot/securityRenew/src/main/resources/static/image/"+mpf.getOriginalFilename();
            System.out.println("파일저장fullpath : "+fullpath);
            boardDto.setStorefilename(fullpath);
            boardDto.setBwriteday(LocalDate.now());
            boardDto.setBcategory(boardDto.getBcategory().substring(0, boardDto.getBcategory().length() - 1));

            System.out.println("mpf.getOriginalFilename() : "+mpf.getOriginalFilename());
            mpf.transferTo(new java.io.File(fullpath));
            boardDto.setUploadfilename(mpf.getOriginalFilename());
            Board board=boardDto.toEntity();
            boardService.save(board);
        }
        else {
            boardDto.setBwriteday(LocalDate.now());
            boardDto.setBcategory(boardDto.getBcategory().substring(0, boardDto.getBcategory().length() - 1));
            Board board = boardDto.toEntity();
            boardService.save(board);
        }

        return "redirect:/board";
    }

    @GetMapping("board_detail_do")
    public String board_detail(@RequestParam("bno") Long bno, Model model) {
        boardService.up_readcnt(bno);
        Board board2 = boardService.detail(bno);
        System.out.println("글번호 : "+board2.getBno());
        System.out.println("그림 위치 : "+board2.getUploadfilename());
        if (board2 != null) {
            model.addAttribute("bno", board2.getBno());
            model.addAttribute("btitle", board2.getBtitle());
            model.addAttribute("bwriter", board2.getBwriter());
            model.addAttribute("bcategory", board2.getBcategory());
            model.addAttribute("bwriteday", board2.getBwriteday());
            model.addAttribute("bcontent", board2.getBcontent());
            model.addAttribute("storefilename", board2.getStorefilename());
            model.addAttribute("uploadfilename", board2.getUploadfilename());
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
    @ResponseBody
    public void board_del(HttpServletRequest request) {
        System.out.println("삭제됨?");
        Long bno = Long.parseLong(request.getParameter("bno"));
        boardService.board_del(bno);
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
        mo.addAttribute("storefilename", board.getStorefilename());
        mo.addAttribute("uploadfilename", board.getUploadfilename());

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

    @GetMapping("/hello_board")
    public String hello_board() {
        return "hello_board";
    }

    @RequestMapping(value = "/hello_board_in", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello_board_in(HelloDto helloDto,Model model, @RequestParam(required = false, defaultValue = "0", value = "page") int page) {
        Hello hello = helloDto.toEntity();
        helloService.save(hello);

        Page<Hello> helloPage = helloService.listpage(page);
        int totalPage = helloPage.getTotalPages();
        int nowpage = helloPage.getPageable().getPageNumber() + 1;//현재페이지
        model.addAttribute("nowpage", nowpage);
        model.addAttribute("list", helloPage.getContent());
        model.addAttribute("totalPage", totalPage);
        List<Hello> list = helloService.out();
        return "redirect:/hello_board";
    }
}