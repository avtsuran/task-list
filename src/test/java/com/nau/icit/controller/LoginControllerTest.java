package com.nau.icit.controller;

import com.nau.icit.model.User;
import com.nau.icit.service.UserAuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    public static final String FIRST_NAME = "firstName";
    public static final String SECOND_NAME = "secondName";
    public static final String ORDER_WORD = "password";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String REGISTRATION = "registration";
    @InjectMocks
    private LoginController loginController;
    @Mock
    private View mockView;
    @Mock
    private UserAuthService userAuthService;
    private MockMvc mockMvc;
    private User invalidUser, validUser;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(loginController)
                .setSingleView(mockView)
                .build();
        invalidUser = new User();
        invalidUser.setId(1L);
        invalidUser.setFirstName("F");
        invalidUser.setSecondName("S");
        invalidUser.setLogin("L");
        invalidUser.setEmail("E");
        invalidUser.setPassword("P");

        validUser = new User();
        validUser.setId(2L);
        validUser.setFirstName("John");
        validUser.setSecondName("Doe");
        validUser.setLogin("john1990");
        validUser.setEmail("john1990@gmail.com");
        validUser.setPassword("john1990");
    }

    @Test
    public void shouldReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN));
    }

    @Test
    public void shouldReturnRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION));
    }

    @Test
    public void shouldReturnRegistrationPageIfUserIsInvalid() throws Exception {
        when(userAuthService.findUserByLogin(invalidUser.getLogin())).thenReturn(invalidUser);
        mockMvc.perform(post("/registration")
                    .param(FIRST_NAME, invalidUser.getFirstName())
                    .param(SECOND_NAME, invalidUser.getSecondName())
                    .param(ORDER_WORD, invalidUser.getPassword())
                    .param(LOGIN, invalidUser.getLogin())
                    .param(EMAIL, invalidUser.getEmail()))
                .andExpect(model().errorCount(6))
                .andExpect(status().isOk())
                .andExpect(view().name(REGISTRATION));
    }

    @Test
    public void shouldReturnLoginPageAfterSuccessfulRegistration() throws Exception {
        when(userAuthService.findUserByLogin(anyString())).thenReturn(null);
        mockMvc.perform(post("/registration")
                    .param(FIRST_NAME, validUser.getFirstName())
                    .param(SECOND_NAME, validUser.getSecondName())
                    .param(ORDER_WORD, validUser.getPassword())
                    .param(LOGIN, validUser.getLogin())
                    .param(EMAIL, validUser.getEmail()))
                .andExpect(model().errorCount(0))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN));
    }
}
