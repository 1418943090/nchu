package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.tool.SignInTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.Net;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
@RestController
public class Sign_inController {
    @Autowired
    Sign_in_TimeServer sign_in_timeServer;
    @Autowired
    Sign_in_StatusServer sign_in_statusServer;

    @Value("${spring.net.public.ip}")
    String public_ip;

    @GetMapping("sign_in/show/all/{today}")
    public ModelAndView show(@PathVariable String today, Model model){

        List<Sign_in_Status> list = new ArrayList<>();
        if(today.equals("today")){
          list = sign_in_statusServer.getSign_in_StatusByDate(MyDate.getDate());
        }
        model.addAttribute("sign_in_time",SignInTool.getTime(sign_in_timeServer));
        model.addAttribute("list",list);
        return new ModelAndView("signinshow","model",model);
    }

    //用户点击签到后，判断是否满足签到条件不满足返回原因，满足后将签到时间保存到签到状态表中
    @GetMapping("/sign_in/in/{username}")
    public ErrorVo in(@PathVariable String username, HttpServletRequest request){

      ErrorVo errorVo = new ErrorVo("");
        //ip校验
      String ip = Net.getIP(request);
      if(!ip.equals(public_ip)){
        errorVo.setData("签到失败,不是实验室环境网络");
        return errorVo;
      }
       boolean isSignIn = false;
       boolean signIn = false;
       String date = MyDate.getDate();
       int signTime  = MyDate.getTimeInt();
       String signTimeStr = MyDate.getTimeString();
       Sign_in_Status sign_in_status = sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(username,date);
       HashMap<String,int[]> hash = MyDate.getTimeTable(SignInTool.getTime(sign_in_timeServer));
       Set<String> s = hash.keySet();
       for(String a : s){
           System.out.println(a);
           System.out.println(hash.get(a)[0]+"　"+hash.get(a)[1]);
           if (signTime >= hash.get(a)[0] && signTime<=hash.get(a)[1]) {
               switch (a){
                   case "mon_in":   if(sign_in_status.getMon_in().equals("未签到")) { sign_in_status.setMon_in(signTimeStr); } else{ isSignIn = true; } break;
                   case "mon_out":  if(sign_in_status.getMon_out().equals("未签到")) { sign_in_status.setMon_out(signTimeStr); } else{ isSignIn = true; } break;
                   case "aft_in":   if(sign_in_status.getAft_in().equals("未签到")) { sign_in_status.setAft_in(signTimeStr); } else{ isSignIn = true; } break;
                   case "aft_out":  if(sign_in_status.getAft_out().equals("未签到")) { sign_in_status.setAft_out(signTimeStr); } else{ isSignIn = true; } break;
                   case "eve_in":   if(sign_in_status.getEve_in().equals("未签到")) { sign_in_status.setEve_in(signTimeStr); } else{ isSignIn = true; } break;
                   case "eve_out":  if(sign_in_status.getEve_out().equals("未签到")) { sign_in_status.setEve_out(signTimeStr); } else{ isSignIn = true; } break;
                   default:break;
               }
               if(isSignIn == false) {
                   signIn = true;
                   sign_in_statusServer.save(sign_in_status);//将签到信息存储到数据库中
               }
             break;
           }
       }
       if(isSignIn==true){
           errorVo.setData("你已签到,不要重复签到哦!");
           return errorVo;
       }
       if(signIn == false){
           errorVo.setData("还没到签到时间哦");
           return errorVo;
       }
       return errorVo;
    }

    //用户点击签到信息后，返回签到状态信息
    //如果是新的一天则将空的签到信息存到数据中，确保有数据返回给前台
    @GetMapping("/sign_in/{username}")
    public ModelAndView Sign_in(@PathVariable String username, Model model){

        String s = MyDate.getDate();
        Sign_in_Status sign_in_status = sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(username,s);
        if(sign_in_status==null){
           sign_in_status = new Sign_in_Status(username,s);
           sign_in_statusServer.save(sign_in_status);
        }
        model.addAttribute("sign_in_time", SignInTool.getTime(sign_in_timeServer));
        model.addAttribute("sign_in_status",sign_in_status);
        return new ModelAndView("signin","model",model);
    }
}
