package com.love.nchu.controller;


import com.love.nchu.demain.GlobalVariable;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.vo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserInfoServer userInfoServer;

    @GetMapping("user/{username}")
    public ModelAndView user(@PathVariable("username") String username, Model model){

        UserInfo userInfo = userInfoServer.getUserByUsername(username);

        List<Menu> list = new ArrayList();
        list.add(new Menu("基本信息","/basic_information/"+username));
        list.add(new Menu("发表论文","/userPapers/"+username));
        list.add(new Menu("学术研究",""));

        list.add(new Menu("产品","/protect/"+username));
        System.out.println("aaaa");
        System.out.println(userInfo.toString());
        if(userInfo.getIdentity().equals("student"))
        {
            list.add(new Menu("签到系统","/sign_in/"+username));
        }else if(userInfo.getIdentity().equals("teacher")){
            list.add(new Menu("研究生",""));
            list.add(new Menu("签到情况","/sign_in"));
            list.add(new Menu("注册申请","/review"));
            list.add(new Menu("自定义标题","/title_edit"));
        }
        model.addAttribute("list",list);
        model.addAttribute("TitleEdit", GlobalVariable.titleEdit);
       return new ModelAndView("person_center","model",model);
    }
}
