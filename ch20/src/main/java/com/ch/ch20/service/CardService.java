package com.ch.ch20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ch.ch20.entity.Card;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CardService extends IService<Card> {
    String selectAllCards(int currentPage, Model model, HttpSession session);
    String add(Card card, HttpServletRequest request, String act, HttpSession session);
    String detail(Integer id,String act,Model model);
    String delete(Integer id);
}
