package com.nau.icit.controller;

import com.nau.icit.model.Board;
import com.nau.icit.model.Task;
import com.nau.icit.model.TaskList;
import com.nau.icit.model.User;
import com.nau.icit.repository.BoardRepository;
import com.nau.icit.repository.TaskListRepository;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
    private MockMvc mockMvc;
    private User user;
    private List<Board> boards;
    private TaskList taskList;
    private Board board;
    @Before
    public void setUp() {
        mockMvc = standaloneSetup(boardController)
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
        taskList = new TaskList();
        taskList.setName("To do");
        taskList.setBoard(board);
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

    @Test
    public void shouldReturnBoardPage() throws Exception{
        mockMvc.perform(get("/board?id=" + board.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("board"));
    }

    @Test
    public void shouldEditBoardNameAndReturnBoardPage() throws Exception{
        mockMvc.perform(post("/board")
                    .param("id", board.getId().toString())
                    .param("name", board.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + board.getId()));
    }

    @Test
    public void shouldAddTaskListAndReturnBoardPage() throws Exception{
        when(boardRepository.findBoardById(1L)).thenReturn(board);
        mockMvc.perform(post("/add-task-list?id=1")
                    .param("name", taskList.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/board?id=" + board.getId()));
    }
}
