package com.love.nchu.tool;

import com.love.nchu.demain.Sign_in_Time;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.vo.MyDate;

public class SignInTool {

    public static  Sign_in_Time sign_in_time;


    protected static void saveTime(Sign_in_TimeServer sign_in_timeServer){

        Sign_in_Time s = new Sign_in_Time("(7:30~8:10)","(11:35~11:50)","(13:30~14:10)","(17:35~17:50)",
                "(18:30~19:10)","(20:55~23:00)","5-1","9-30","summer");


        Sign_in_Time s2 = new Sign_in_Time("(7:30~8:10)","(11:35~11:50)","(13:00~13:40)","(17:05~17:20)",
                "(18:30~19:10)","(20:55~23:00)","10-1","4-30","spring");

        sign_in_timeServer.save(s);
        sign_in_timeServer.save(s2);

    }



    public static Sign_in_Time getTime(Sign_in_TimeServer sign_in_timeServer){

        if(sign_in_time==null){
            if(sign_in_timeServer.count()<2){
                 saveTime(sign_in_timeServer);
            }
        int month = MyDate.getMonth();
        if(month>=5 && month<10){
            sign_in_time= sign_in_timeServer.getTimeBySeason("summary");

        }else{
            sign_in_time = sign_in_timeServer.getTimeBySeason("spring");
        }
        }
        return sign_in_time;
    }
}
