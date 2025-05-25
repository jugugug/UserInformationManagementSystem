package com.ch.ch20.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Method;


/**
 * 异常统一处理
 */
@ControllerAdvice
public class GlobalExceptionHandleController {
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        String message ="";
        if(e instanceof NoLoginException){
            message="nologin";
        }else {
            //未知异常
            message="noError";
        }
        model.addAttribute("mymessage",message);
        return "myerror";
    }
}
