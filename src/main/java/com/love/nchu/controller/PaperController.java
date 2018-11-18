package com.love.nchu.controller;

import com.love.nchu.service.PaperServer;
import com.love.nchu.vo.deletePaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class PaperController {

    @Autowired
    private PaperServer paperServer;
    //删除论文控制器
   @PostMapping("/delete/paper")
    public ModelAndView deletePaper(@RequestBody deletePaperVo deletePaperVo){
        System.out.println(deletePaperVo.toString());
        for(Integer i : deletePaperVo.getListPaperId()){
            paperServer.deletePaper(i);
        }
       return new ModelAndView("redirect:/papers/"+deletePaperVo.getUserName());
    }

    @PostMapping("/update/paper")
    public String updatePaper(int updateId,String updateTitle,String updateUsername){

       System.out.println(updateId+" "+updateTitle+" "+updateUsername);
       paperServer.updatePaper(updateId,updateTitle);
       return null;
    }

}
