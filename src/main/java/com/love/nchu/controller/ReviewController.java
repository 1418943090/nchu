package com.love.nchu.controller;

import com.love.nchu.demain.ReviewTable;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.ReviewTableServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    ReviewTableServer reviewTableServer;
    @Autowired
    UserServer userServer;
    @Autowired
    UserInfoServer userInfoServer;

    @PostMapping("/review/del")
    public ModelAndView del(@RequestBody List<ReviewTable> list){

        for(ReviewTable r : list){
            System.out.println(r.toString());
            if(!r.getEnding().equals("通过")) {
               userServer.delUserByUsername(r.getUsername());
                userInfoServer.delUserInfoByUsername(r.getUsername());
            }
            reviewTableServer.delReviewTableByUsername(r.getUsername());
        }
        return new ModelAndView("redirect:/review");
    }
    @GetMapping("/review")
    public ModelAndView review(Model model){

        List<ReviewTable> list =  reviewTableServer.getAll();
        List<UserInfo> list2 = userInfoServer.getAll();
        model.addAttribute("list",list);
        model.addAttribute("list2",list2);
        System.out.println("AAAAA");
        return new ModelAndView("review","model",model);
    }
    @PostMapping("/review/refuse")
    public ModelAndView refuse(@RequestBody  ReviewTable reviewTable){
        reviewTable.setStatus("已处理");
        reviewTable.setEnding("拒绝");
        reviewTable.setCla("danger");
        reviewTableServer.save(reviewTable);
        return new ModelAndView("redirect:/review");
    }
    @PostMapping("/review/pass")
    public ModelAndView pass(@RequestBody  ReviewTable reviewTable){
        User user = userServer.findUserByUsername(reviewTable.getUsername());
        user.setEnabled(true);
        userServer.save(user);
        reviewTable.setStatus("已处理");
        reviewTable.setEnding("通过");
        reviewTable.setCla("success");
        reviewTableServer.save(reviewTable);
        return new ModelAndView("redirect:/review");
    }
}
