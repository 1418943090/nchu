package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.service.MailServer;
import com.love.nchu.service.TitleEditServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.tool.EmailTool;
import com.love.nchu.tool.PasswordTool;
import com.love.nchu.tool.RegistryTool;
import com.love.nchu.tool.TitleTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class PasswordController {

    Date date;

    @Autowired
    MailServer mailServer;
    String code;

    @Autowired
    UserServer userServer;

    @Autowired
    TitleEditServer titleEditServer;

    @GetMapping("/password/login")
    public ModelAndView login(){

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/password/index")
    public ModelAndView index(){

        return new ModelAndView("redirect:/index");
    }
    @GetMapping("/ForgetOrChangePassword")
    public ModelAndView ForgetOrChangePassword(Model model){
        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
       return new ModelAndView("ForgetOrChangePassword","model",model);
    }
    @PostMapping("/password/getVcode")
    public void emailValidator(@RequestBody  String email){
        date = new Date();
        System.out.println(email);
        code = EmailTool.getCode();
        mailServer.sendSimpleMail(email,"邮箱验证","本次的验证码为(十分钟内有效):"+code);
    }

    @PostMapping("/password/step1")
    public ErrorVo vcodeCheck(@RequestBody String vcode){

         ErrorVo errorVo = new ErrorVo("");


         if(!PasswordTool.vcoedCheck(code,vcode)||date==null){
             errorVo.setData("验证码错误");
             return errorVo;
         }
        if(!EmailTool.isNotExpiredCheck(date,new Date())){
            errorVo.setData("验证码已过期，请重新获取");//验证码过期

        }
         return errorVo;
    }
    @GetMapping("/password/step2")
    public ModelAndView updatePassword(Model model){
        model.addAttribute("TitleEdit",TitleTool.getTitle(titleEditServer));
       return new ModelAndView("updatepassword","model",model);
    }

    @PostMapping("/password/updatepassword")
    public ErrorVo updatePassword2(@RequestBody String firstpassword,HttpServletRequest request){

        String username="";
        ErrorVo errorVo = new ErrorVo("");
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie2 : cookies){
                if(cookie2.getName().equals("user")){
                   username = cookie2.getValue();
                }
            }
        }
        try {
            if (RegistryTool.NewAndOldPasswordEqualsCheck(userServer, username, firstpassword)) {
                errorVo.setData("新密码不能和原来一样");
            }
            else{
              PasswordTool.updatePassword(userServer,username,firstpassword);
            }
        }catch(Exception e){
            e.printStackTrace();
            errorVo.setData("服务器错误，操作失败");
        }
        return errorVo;
    }

    @GetMapping("/password/success")
    public ModelAndView succeess(Model model){
        model.addAttribute("TitleEdit",TitleTool.getTitle(titleEditServer));
        return new ModelAndView("changePasswordSuccess","model",model);
    }
}
