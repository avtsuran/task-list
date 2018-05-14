package com.nau.icit.controller;

import com.nau.icit.model.Board;
import com.nau.icit.model.Task;
import com.nau.icit.model.TaskList;
import com.nau.icit.model.User;
import com.nau.icit.repository.BoardRepository;
import com.nau.icit.repository.TaskListRepository;
import com.nau.icit.repository.TaskRepository;
import com.nau.icit.service.UserAuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class BoardControllerTest {

    @InjectMocks
    private BoardController boardController;
    @Mock
    private View mockView;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private TaskListRepository taskListRepository;
    @Mock
    private UserAuthService userAuthService;
    @Mock
    private TaskRepository taskRepository;
    private MockMvc mockMvc;
    private User user;
    private List<Board> boards;
    private TaskList taskList;
    private Board board;
    private Task task;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(boardController)
                .setSingleView(mockView)
                .build();
        user = new User();
        board = new Board();
        board.setId(11L);
        board.setUser(user);
        board.setName("Java");
        boards = new ArrayList<Board>();
        boards.add(board);
        boards.add(new Board());
        taskList = new TaskList();
        taskList.setId(243L);
        taskList.setName("To do");
        taskList.setBoard(board);
        task = new Task();
        task.setId(9L);
        task.setName("JPA");
        task.setTaskList(taskList);
    }

    @Test
    public void shouldReturnToBoardPage() throws Exception{
        mockMvc.perform(get("/board?id=" + board.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("board"));
    }

    @Test
    public void shouldEditBoardNameAndReturnToBoardPage() throws Exception{
        when(userAuthService.getAuthUser()).thenReturn(user);
        ArgumentCaptor<Board> captor = ArgumentCaptor.forClass(Board.class);

        mockMvc.perform(post("/board")
                    .param("id", board.getId().toString())
                    .param("name", "New name"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + board.getId()));

        verify(boardRepository).save(captor.capture());
        assertEquals(board.getId(), captor.getValue().getId());
        assertEquals("New name", captor.getValue().getName());

    }

    @Test
    public void shouldAddTaskListAndReturnToBoardPage() throws Exception{
        when(boardRepository.findBoardById(board.getId())).thenReturn(board);
        ArgumentCaptor<TaskList> captor = ArgumentCaptor.forClass(TaskList.class);

        mockMvc.perform(post("/add-task-list?id=" + board.getId())
                    .param("name", taskList.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + board.getId()));

        verify(taskListRepository).save(captor.capture());
        assertEquals(taskList.getName(), captor.getValue().getName());
        assertEquals(taskList.getBoard().getId(), captor.getValue().getBoard().getId());
    }

    @Test
    public void shouldEditTaskListAndReturnToBoardPage() throws Exception{
        when(taskListRepository.findTaskListById(taskList.getId())).thenReturn(taskList);
        ArgumentCaptor<TaskList> captor = ArgumentCaptor.forClass(TaskList.class);

        mockMvc.perform(post("/edit-task-list?id=" + taskList.getId())
                    .param("name", "New name"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + board.getId()));

        verify(taskListRepository).save(captor.capture());
        assertEquals(taskList.getId(), captor.getValue().getId());
        assertEquals("New name", captor.getValue().getName());
        assertEquals(taskList.getBoard().getId(), captor.getValue().getBoard().getId());
    }

    @Test
    public void shouldRemoveTaskListAndReturnToBoardPage() throws Exception{
        when(taskListRepository.findTaskListById(taskList.getId())).thenReturn(taskList);
        ArgumentCaptor<TaskList> captor = ArgumentCaptor.forClass(TaskList.class);

        mockMvc.perform(get("/remove-task-list?id=" + taskList.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + board.getId()));

        verify(taskRepository).deleteAllByTaskList(captor.capture());
        verify(taskListRepository).delete(captor.capture());
    }

    @Test
    public void shouldAddTaskAndReturnToBoardPage() throws Exception{
        when(taskListRepository.findTaskListById(taskList.getId())).thenReturn(taskList);
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);

        mockMvc.perform(post("/add-task?id=" + taskList.getId())
                    .param("name", "JPA"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + taskList.getBoard().getId()));

        verify(taskRepository).save(captor.capture());
        assertNull(captor.getValue().getId());
        assertNull(captor.getValue().getDescription());
        assertEquals(task.getName(), captor.getValue().getName());
        assertEquals(task.getTaskList().getId(), captor.getValue().getTaskList().getId());
    }
}
