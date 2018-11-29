//package com.love.nchu.controller;
//
//import com.love.nchu.service.PermissionServer;
//import com.love.nchu.vo.PerVo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@RestController
//public class PermissionController {
//
//
//    @Autowired
//    PermissionServer permissionServer;
//    @GetMapping
//    public ModelAndView Permission(Model model){
//
//        List<PerVo> list = permissionServer.getPerVo();
//
//        for(PerVo p : list){
//
//            if(p.isAccountNonLocked().equals("锁定")){
//                p.setCla("danger");
//            }
//            else{
//                p.setCla("success");
//            }
//
//        }
//
//        model.addAttribute("list",list);
//
//        return new ModelAndView("permission","model",model);
//    }
//}
