package com.nau.icit.controller;

import com.nau.icit.model.Task;
import com.nau.icit.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    @InjectMocks
    private TaskController taskController;
    @Mock
    private View mockView;
    @Mock
    private TaskRepository taskRepository;
    private MockMvc mockMvc;
    private Task task;


    @Before
    public void setUp() {
        mockMvc = standaloneSetup(taskController)
                .setSingleView(mockView)
                .build();
        task = new Task();
        task.setId(1L);
        task.setPriority(1);
        task.setName("Spring Boot.");
        task.setDescription("Spring Boot is Spring's convention-over-configuration solution for creating stand-alone.");
    }

    @Test
    public void shouldReturnToTaskPage() throws Exception {
        when(taskRepository.findTaskById(task.getId())).thenReturn(task);
        mockMvc.perform(get("/task?id=" + task.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("task"));
    }
}
