package com.love.nchu.controller;


import com.love.nchu.vo.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("user/{username}")
    public ModelAndView user(@PathVariable("username") String username, Model model){

        List<Menu> list = new ArrayList();
        list.add(new Menu("基本信息","/basic_information/"+username));
        list.add(new Menu("发表论文","/papers/"+username));
        list.add(new Menu("学术研究",""));
        list.add(new Menu("研究生",""));
        list.add(new Menu("产品","/protect"+username));
        model.addAttribute("list",list);
       return new ModelAndView("person_center","model",model);
    }
}
