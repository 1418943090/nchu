package com.love.nchu.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class pathController {
    @RequestMapping("/path/{path}")
    public ModelAndView get(@PathVariable String path){
        return new ModelAndView(path);
    }
}
