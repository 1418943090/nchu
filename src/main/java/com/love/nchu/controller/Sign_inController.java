package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.tool.SignInTool;
import com.love.nchu.vo.MyDate;
import com.love.nchu.vo.Net;
import com.love.nchu.vo.weekShow;
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

    @Autowired
    UserServer userServer;
    @Value("${spring.net.public.ip}")
    String public_ip;

    @GetMapping("/sign_in/show/week/{date}")
    public ModelAndView weekShow(Model model,@PathVariable  String date) throws Exception{

        System.out.println("afa");
        System.out.println(date);

        Sign_in_Status sign_in_status ;

       List<String> list = MyDate.getWeekDays(date,"yyyy-MM-dd",true);

       List<weekShow> list2 = new ArrayList<>();
       //System.out.print(list2);

         List<Sign_in_Status> list3 = new ArrayList<>();

         List<String> user = userServer.getAllUsername();
        for(int i=0;i<user.size();i++){


            weekShow w = new weekShow(user.get(i),0,0,0,0,0,0,0);

           if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(0)))==null){
             w.setMonday(6);
           }else{
               w.setMonday(sign_in_status.getCount());
           }

            if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(1)))==null){
                w.setTuesday(6);
            }else{
                w.setTuesday(sign_in_status.getCount());
            }


            if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(2)))==null){
                w.setWednesday(6);
            }else{
                w.setWednesday(sign_in_status.getCount());
            }



            if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(3)))==null){
                w.setThursday(6);
            }else{
                w.setThursday(sign_in_status.getCount());
            }

            if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(4)))==null){
                w.setFriday(6);
            }else{
                w.setFriday(sign_in_status.getCount());
            }

            if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(5)))==null){
                w.setSaturday(6);
            }else{
                w.setSaturday(sign_in_status.getCount());
            }

            if(( sign_in_status =  sign_in_statusServer.getSign_in_StatusByUsernaemAndDate(user.get(i),list.get(6)))==null){
                w.setSunday(6);
            }else{
                w.setSunday(sign_in_status.getCount());
            }

            list2.add(w);
            }

            System.out.println(list2);

        model.addAttribute("date",list);
        model.addAttribute("list",list2);
       // return null;
        return new ModelAndView("signinshow_week","model",model);
    }


    @GetMapping("/sign_in/show/all/{date}")
    public ModelAndView show(@PathVariable String date, Model model){

        String d = "";
        List<Sign_in_Status> list = new ArrayList<>();
        if(date.equals("today")){
            d = MyDate.getDate();
            list = sign_in_statusServer.getSign_in_StatusByDate(d);


        }
        else{
            list = sign_in_statusServer.getSign_in_StatusByDate(date);
            d = date;
        }

        model.addAttribute("sign_in_time",SignInTool.getTime(sign_in_timeServer));
        model.addAttribute("list",list);
        model.addAttribute("date",d);
        return new ModelAndView("signinshow","model",model);
    }

    //用户点击签到后，判断是否满足签到条件不满足返回原因，满足后将签到时间保存到签到状态表中
    @GetMapping("/sign_in/in/{username}")
    public ErrorVo in(@PathVariable String username, HttpServletRequest request){

      ErrorVo errorVo = new ErrorVo("");
        //ip校验
      String ip = Net.getIP(request);
//      if(!ip.equals(public_ip)){
//        errorVo.setData("签到失败,不是实验室环境网络");
//        return errorVo;
//      }
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
                  // sign_in_status.setCount(sign_in_status.getCount()-1);
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
