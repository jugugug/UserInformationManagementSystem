package com.ch.ch20.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ch.ch20.entity.User;
import com.ch.ch20.mapper.UserMapper;
import com.ch.ch20.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Override
    public String register(User user) {
        user.setUpwd(MD5Util.MD5(user.getUpwd()));
        if(save(user))
            return "login";
        return "register";
    }
    @Override
    public String checkUname(User user) {
        //条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("uname", user.getUname());
        List<User> userList = list(wrapper);
        if(userList.size() > 0)
            return "no";
        return "ok";
    }
    @Override
    public String login(User user, Model model, HttpSession session) {
        String rand = (String)session.getAttribute("rand");
        if(!rand.equalsIgnoreCase(user.getCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "login";
        }
        user.setUpwd(MD5Util.MD5(user.getUpwd()));
        //条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String,String> map = new HashMap<>();
        map.put("uname", user.getUname());
        map.put("upwd", user.getUpwd());
        wrapper.allEq(map);
        List<User> userList = list(wrapper);
        if(userList.size() > 0){
            session.setAttribute("userLogin", userList.get(0));
            return "forward:/card/selectAllCards?currentPage=1";
        }
        model.addAttribute("errorMessage", "用户名或密码错误！");
        return "login";
    }
    @Override
    public String updateUpwd(User user) {
        user.setUpwd(MD5Util.MD5(user.getUpwd()));
        updateById(user);
        return "login";
    }
}
