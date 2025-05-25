package com.ch.ch20.controller;

import com.ch.ch20.entity.User;
import com.ch.ch20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String toLogin(@ModelAttribute User user) {
        return "login";
    }

    @GetMapping("/toRegister")
    public String toRegister(@ModelAttribute User user){
        return "register";
    }
    @PostMapping("/checkUname")
    @ResponseBody
    public String checkUname(@RequestBody User user){
        return userService.checkUname(user);
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session){
        return userService.login(user,model,session);
    }
}
