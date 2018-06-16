package com.nau.icit.controller;

import com.nau.icit.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task")
    public String getTask(@RequestParam Long id, ModelMap modelMap) {
        modelMap.addAttribute("task", taskRepository.findTaskById(id));
        return "task";
    }
}
