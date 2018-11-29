package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.demain.editUserInfo;
import com.love.nchu.service.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class basicInformationController {


    @Autowired
    UserInfoServer userInfoServer;

    @RequestMapping("/update/basic_information")
    public ErrorVo update(@RequestBody  editUserInfo userInfo, Model model){


        ErrorVo error = new ErrorVo("");
        UserInfo u = userInfoServer.getUserByEmail(userInfo.getEditEmail());

        if(u!=null && !u.getUsername().equals(userInfo.getEditUsername())){
            error.setData("邮箱已被注册");
        }else{

            u = userInfoServer.getUserByTel((userInfo.getEditTel()));
            if(u!=null && !u.getUsername().equals(userInfo.getEditUsername()))
            {
                error.setData("电话号码已被注册");
            }
        }
        System.out.println(userInfo);
        if(error.getData().equals("")){
            userInfoServer.updateUserInfo(userInfo);
        }
        return error;
    }
}
