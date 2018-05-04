package com.nau.icit.controller;

import com.nau.icit.model.Board;
import com.nau.icit.repository.BoardRepository;
import com.nau.icit.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/board-list")
    public String getBoards(ModelMap modelMap){
        modelMap.addAttribute("boards", boardRepository.findBoardsByUser(userAuthService.getAuthUser()));
        modelMap.addAttribute("board", new Board());
        return "board-list";
    }

    @PostMapping("/board-list")
    public String saveBoard(Board board){
        board.setUser(userAuthService.getAuthUser());
        boardRepository.save(board);
        return "redirect:/board-list";
    }
}
