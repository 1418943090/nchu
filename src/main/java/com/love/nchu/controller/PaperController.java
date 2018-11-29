package com.love.nchu.controller;

import com.love.nchu.demain.GlobalVariable;
import com.love.nchu.demain.Paper;
import com.love.nchu.service.PaperServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.vo.deletePaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class PaperController {
    @Autowired
    private PaperServer paperServer;

    @Autowired
    private UserInfoServer userInfoServer;
    //删除论文控制器
   @PostMapping("/delete/paper")
    public ModelAndView deletePaper(@RequestBody deletePaperVo deletePaperVo){
        for(Integer i : deletePaperVo.getListPaperId()){
            paperServer.deletePaper(i);
        }
       return new ModelAndView("redirect:/userPapers/"+deletePaperVo.getUserName());
    }
    @PostMapping("/update/paper")
    public String updatePaper(int updateId,String updateTitle,String updateUsername){
       paperServer.updatePaper(updateId,updateTitle);
       return null;
    }
    @GetMapping("/papers")
    public ModelAndView papers(Model model){


       List<Paper> list = paperServer.getAllPapers();
       model.addAttribute("list",list);
       System.out.println(list);
       model.addAttribute("TitleEdit", GlobalVariable.titleEdit);
       return new ModelAndView("papers","model",model);
    }

    @GetMapping("/papers/search/{condition}/{value}")
    public ModelAndView search(@PathVariable String condition,@PathVariable  String value,Model model){
       System.out.println(condition+" "+value);
       if(condition.equals("all")){
           List<Paper> list =  paperServer.getPaperByNameLikeOrTitleLike(value);
           System.out.println(list);
           model.addAttribute("list",list);
       }
       else if(condition.equals("author")){
           List<Paper> list = paperServer.getPaperByNameLike(value);
           System.out.println(list);
           model.addAttribute("list",list);
       }else if(condition.equals("title")){
           List<Paper> list = paperServer.getPaperByTitleLike(value);
           System.out.println(list);
           model.addAttribute("list",list);
       }
       return new ModelAndView("papers","model",model);
    }

}
