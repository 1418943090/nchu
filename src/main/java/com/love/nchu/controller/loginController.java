package com.love.nchu.controller;

import com.love.nchu.demain.User;
import com.love.nchu.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.love.nchu.security.SHAencrypt;

import static com.love.nchu.security.SHAencrypt.*;

@RestController
public class loginController {

    @Autowired
    UserServer userServer;

    @GetMapping("/login_error")
    public ModelAndView login_error(Model model){
        System.out.println("login_error");
        model.addAttribute("errorstatus","用户名或密码错误");
        model.addAttribute("errorstatus","用户名或密码错误");
        return new ModelAndView("login","login",model);
    }
  @RequestMapping(value="/login",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView login(){
        return new ModelAndView("login");
  }

  @RequestMapping(value = "/login_valid",method = {RequestMethod.POST})
  public ModelAndView logining(String username,String password,HttpServletResponse response) throws Exception {

      User user = userServer.findUserByUsername(username);
      if (user != null) {
          if (user.getPassword().equals(SHAencrypt.encryptSHA(password))) {
              Cookie cookie = new Cookie("user", username);
              cookie.setPath("/");
              response.addCookie(cookie);
              return new ModelAndView("redirect:/index");
          }
      }
      return new ModelAndView("redirect:/login_error");
  }
   @PostMapping("/login_success")
    public ModelAndView loginsuccess(HttpServletRequest request,
                                     HttpServletResponse response){
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/log_out")
    public ModelAndView logout(HttpServletResponse response)
    {
        Cookie cookie = new Cookie("user","");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ModelAndView("redirect:/index");
    }
}

