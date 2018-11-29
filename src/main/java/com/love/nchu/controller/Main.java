package com.love.nchu.controller;
import com.love.nchu.demain.GlobalVariable;
import com.love.nchu.demain.TitleEdit;
import com.love.nchu.service.TitleEditServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
@RestController
public class Main {

    @Autowired
    TitleEditServer titleEditServer;

    @GetMapping("/")
    public ModelAndView main(){
        return new ModelAndView("redirect:/index");
    }
    @GetMapping(value = "/index")
    public ModelAndView index(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie2 : cookies){
                if(cookie2.getName().equals("user")){
                    model.addAttribute("username",cookie2.getValue());
                }
            }
        }

        TitleEdit titleEdit = titleEditServer.getTitle(1);
        GlobalVariable.titleEdit = titleEdit;
        model.addAttribute("TitleEdit",titleEdit);


        return new ModelAndView("index","login-success",model);
    }
    @GetMapping("/more")
    public ModelAndView getMore(){
        return new ModelAndView("more");
    }
    @GetMapping("/forgetPassword")
    public ModelAndView forgetPassword(){
        return new ModelAndView("forgetPassword");
    }
}
