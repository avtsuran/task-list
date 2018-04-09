package com.nau.icit.service;

import com.nau.icit.model.Role;
import com.nau.icit.model.User;
import com.nau.icit.repository.RoleRepository;
import com.nau.icit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findUserByLogin(auth.getName());
    }
}