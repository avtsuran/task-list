package com.nau.icit.service;

import com.nau.icit.model.User;

public interface UserAuthService {
    User findUserByLogin(String login);
    void saveUser(User user);
    User getAuthUser();
}
