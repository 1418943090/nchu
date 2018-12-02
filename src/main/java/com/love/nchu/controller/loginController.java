package com.love.nchu.controller;
import com.love.nchu.demain.User;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.tool.TitleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RestController
public class loginController {
    @Autowired
    UserServer userServer;

    @Autowired
    TitleEditServer titleEditServer;
  @RequestMapping(value="/login",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView login(Model model){

        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
        return new ModelAndView("login","model",model);
  }
  @RequestMapping(value = "/login_valid",method = {RequestMethod.POST})
  public String logining(String username,String password,HttpServletResponse response) throws Exception {
      User user = userServer.findUserByUsername(username);

      if (user != null) {

          if(!user.isEnabled()==true){
              return "账号不可用,请等待管理员审核通过";
          }
          else if(user.isAccountNonLocked()==false){
              return "该账号已被管理员禁用,有疑问请联系管理员";
          } else if (user.getPassword().equals(SHAencrypt.encryptSHA(password))) {
              Cookie cookie = new Cookie("user", username);
              cookie.setPath("/");
              response.addCookie(cookie);
              return "success";
          }
      }
          return "账号或密码错误";
  }
   @PostMapping("/login_success")
    public ModelAndView loginsuccess(HttpServletRequest request,
                                     HttpServletResponse response){
        return new ModelAndView("redirect:/index");
    }
    @GetMapping("/log_out")
    public ModelAndView logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("user","");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return new ModelAndView("redirect:/index");
    }
}

