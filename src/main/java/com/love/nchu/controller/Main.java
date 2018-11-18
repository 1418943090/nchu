package com.love.nchu.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class Main {

    @RequestMapping(value = "/index")
    public ModelAndView index(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        for(Cookie cookie2 : cookies){
            if(cookie2.getName().equals("user")){
                model.addAttribute("username",cookie2.getValue());
            }
        }}
//        System.out.println(session.getAttribute("username"));
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println(username);
//        if(username!=null){
//            System.out.println("a");
//            model.addAttribute("username",username);
//        }
        return new ModelAndView("index","login-success",model);
    }
}
