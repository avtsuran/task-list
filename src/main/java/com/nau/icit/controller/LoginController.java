package com.nau.icit.controller;

import com.nau.icit.model.User;
import com.nau.icit.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    private static final String REGISTRATION_SUCCESS = "Registration was success, please login";
    private static final String LOGIN_ERROR =  "*There is already a user registered with the login provided";
    private static final String LOGIN = "login";
    private static final String REGISTRATION = "registration";

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping({"/", "/login"})
    public String login(){
        return LOGIN;
    }

    @GetMapping("/registration")
    public String registration(ModelMap modelMap){
        User user = new User();
        modelMap.addAttribute("user", user);
        return REGISTRATION;
    }

    @PostMapping("/registration")
    public String registration(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        User userExists = userAuthService.findUserByLogin(user.getLogin());
        if (userExists != null)
            bindingResult.rejectValue(LOGIN, "error.user", LOGIN_ERROR);

        if (bindingResult.hasErrors())
            return REGISTRATION;

        userAuthService.saveUser(user);
        modelMap.addAttribute("showMessage", true);
        modelMap.addAttribute("message", REGISTRATION_SUCCESS);
        return LOGIN;
    }

}
