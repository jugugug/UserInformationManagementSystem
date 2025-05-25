package com.ch.ch20.controller;

import com.ch.ch20.entity.Card;
import com.ch.ch20.entity.User;
import com.ch.ch20.service.CardService;
import com.ch.ch20.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    /**
     * 权限控制
     */
    @ModelAttribute
    public void checkLogin(HttpSession session)throws NoLoginException{
        if (session.getAttribute("userLogin")==null){
            throw new NoLoginException();
        }
    }
    @GetMapping("/toAdd")
    public String toAdd(@ModelAttribute Card card){
        return "add";
    }
    @RequestMapping("/selectAllCards")
    public String selectAllCards(int currentPage, Model model,HttpSession session){
        return cardService.selectAllCards(currentPage,model,session);
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Card card, HttpServletRequest request, String act, HttpSession session){
        return cardService.add(card,request,act,session);
    }
    @GetMapping("/detail")
    public String detail(Integer id,String act,Model model){
        return cardService.detail(id,act,model);
    }
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        return cardService.delete(id);
    }
    @GetMapping("/toUpdatePwd")
    public String toUpdatePwd(Model model,HttpSession session){
        User user=(User)session.getAttribute("userLogin");
        model.addAttribute("user",user);
        return "updatePwd";
    }
    @PostMapping("/updateUpwd")
    public String updateUpwd(@ModelAttribute User user){
        return  userService.updateUpwd(user);
    }
    @GetMapping("/loginOut")
    public String loginOut(@ModelAttribute User user,HttpSession session){
        session.invalidate();
        return "login";
    }
}
