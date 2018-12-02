package com.love.nchu.tool;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class EmailTool {
    public static String getCode(){

        Random random = new Random();
        StringBuilder str =  new StringBuilder("");
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k','l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i <6; i++){
            char num = ch[random.nextInt(ch.length)];
            str.append(num);
        }
        return str.toString();
    }

    public static boolean isNotExpiredCheck(Date date1, Date date2){
         if(date2.getTime()-date1.getTime()>1000*10*60){
             return false;
         }
         return true;
    }
}
