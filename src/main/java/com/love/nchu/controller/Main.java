package com.love.nchu.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class Main {

    @RequestMapping("/")
    public ModelAndView main(){
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        for(Cookie cookie2 : cookies){
            if(cookie2.getName().equals("user")){
                model.addAttribute("username",cookie2.getValue());
            }
        }}
        return new ModelAndView("index","login-success",model);
    }

    @RequestMapping("/more")
    public ModelAndView getMore(){
        return new ModelAndView("more");
    }

    @RequestMapping("/forgetPassword")
    public ModelAndView forgetPassword(){
        return new ModelAndView("forgetPassword");
    }
}
