package com.nau.icit.controller;

import com.nau.icit.model.Board;
import com.nau.icit.model.TaskList;
import com.nau.icit.repository.BoardRepository;
import com.nau.icit.repository.TaskListRepository;
import com.nau.icit.repository.TaskRepository;
import com.nau.icit.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardListController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TaskListRepository taskListRepository;
    @Autowired
    private TaskRepository taskRepository;

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

    @Transactional
    @GetMapping("/remove-board")
    public String removeBoard(@RequestParam Long id){
        for(TaskList taskList: taskListRepository.findTaskListsByBoardId(id))
            taskRepository.deleteAllByTaskList(taskList);
        taskListRepository.deleteAllByBoard(boardRepository.findBoardById(id));
        boardRepository.delete(id);
        return "redirect:/board-list";
    }
}
