package com.love.nchu.controller;

import com.love.nchu.demain.TitleEdit;
import com.love.nchu.service.TitleEditServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TitleEditController {

    @Autowired
    TitleEditServer titleEditServer;
    @GetMapping("/title_edit")
    public ModelAndView titleEdit(Model model){

        TitleEdit titleEdit = titleEditServer.getTitle(1);
        model.addAttribute("TitleEdit",titleEdit);
        return new ModelAndView("title_edit","model",model);
    }

    @PostMapping("/title_edit/edit")
    public String edit(TitleEdit titleEdit){
        System.out.print(titleEdit);
        titleEditServer.save(titleEdit);
        return "更改成功";
    }
}
