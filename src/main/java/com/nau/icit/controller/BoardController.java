package com.nau.icit.controller;

import com.nau.icit.model.Board;
import com.nau.icit.model.Task;
import com.nau.icit.model.TaskList;
import com.nau.icit.repository.BoardRepository;
import com.nau.icit.repository.TaskListRepository;
import com.nau.icit.repository.TaskRepository;
import com.nau.icit.service.TaskService;
import com.nau.icit.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TaskListRepository taskListRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/board")
    public String getBoard(@RequestParam Long id, ModelMap modelMap) {
        List<TaskList> lists = taskListRepository.findTaskListsByBoardId(id);
        for (TaskList taskList : lists) {
            Collections.sort(taskList.getTasks(), new TaskService());
        }
        modelMap.addAttribute("list", lists);
        modelMap.addAttribute("board", boardRepository.findBoardById(id));
        modelMap.addAttribute("newList", new TaskList());
        modelMap.addAttribute("task", new Task());
        return "board";
    }

    @Transactional
    @PostMapping("/board")
    public String editBoard(Board board, ModelMap modelMap) {
        board.setUser(userAuthService.getAuthUser());
        boardRepository.save(board);
        modelMap.clear();
        return "redirect:/board?id=" + board.getId();
    }

    @Transactional
    @PostMapping("/add-task-list")
    public String addTaskList(@RequestParam Long id, TaskList taskList) {
        taskList.setBoard(boardRepository.findBoardById(id));
        taskListRepository.save(taskList);
        return "redirect:/board?id=" + taskList.getBoard().getId();
    }

    @Transactional
    @PostMapping("/edit-task-list")
    public String editTaskList(@RequestParam Long id, TaskList edit, ModelMap modelMap) {
        TaskList taskList = taskListRepository.findTaskListById(id);
        taskList.setName(edit.getName());
        taskListRepository.save(taskList);
        modelMap.clear();
        return "redirect:/board?id=" + taskList.getBoard().getId();
    }

    @Transactional
    @GetMapping("/remove-task-list")
    public String removeTaskList(@RequestParam Long id) {
        TaskList taskList = taskListRepository.findTaskListById(id);
        taskRepository.deleteAllByTaskList(taskList);
        taskListRepository.delete(taskList);
        return "redirect:/board?id=" + taskList.getBoard().getId();
    }

    @Transactional
    @PostMapping("/add-task")
    public String addTask(@RequestParam Long id, Task task) {
        task.setId(null);
        task.setPriority(1);
        TaskList taskList = taskListRepository.findTaskListById(id);
        task.setTaskList(taskList);
        taskRepository.save(task);
        return "redirect:/board?id=" + taskList.getBoard().getId();
    }
}
