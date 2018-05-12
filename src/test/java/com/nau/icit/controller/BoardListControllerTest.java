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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class BoardListControllerTest {

    @InjectMocks
    private BoardListController boardListController;
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
        mockMvc = standaloneSetup(boardListController)
                .setSingleView(mockView)
                .build();
        user = new User();
        board = new Board();
        board.setId(1L);
        board.setUser(user);
        board.setName("Java");
        boards = new ArrayList<Board>();
        boards.add(board);
        boards.add(new Board());
    }

    @Test
    public void shouldReturnBoardsPage() throws Exception{
        when(userAuthService.getAuthUser()).thenReturn(user);
        when(boardRepository.findBoardsByUser(user)).thenReturn(boards);
        mockMvc.perform(get("/board-list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("boards", hasSize(2)))
                .andExpect(view().name("board-list"));
    }

    @Test
    public void shouldSaveBoardAndReturnToBoardsPage() throws Exception{
        mockMvc.perform(post("/board-list"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board-list"));
    }

    @Test
    public void shouldRemoveBoardAndReturnToBoardsPage() throws Exception{
        mockMvc.perform(get("/remove-board?id=" + board.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board-list"));
        verify(boardRepository, times(1)).delete(board.getId());
    }
}
