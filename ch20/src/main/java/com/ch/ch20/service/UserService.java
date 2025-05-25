package com.ch.ch20.service;

import com.ch.ch20.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface UserService {
    String register(User user);
    String checkUname(User user);
    String login(User user, Model model, HttpSession session);
    String updateUpwd(User user);
}
