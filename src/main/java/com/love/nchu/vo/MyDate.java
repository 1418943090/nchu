package com.love.nchu.vo;

import com.love.nchu.demain.Sign_in_Time;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public  class MyDate {






   public static String getDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String s = format.format(date);
        return s;
    }

    public static String getTimeString(){

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String s = format.format(date);
        return s;
    }

    public static int getTimeInt(){
        Date date  = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return hour*100+minute;
    }



    public static int[] get(String time){
        String[] a = time.split("~");
	    a[0] = a[0].replace("(","");
	    a[0] = a[0].replace(":","");
        a[1] = a[1].replace(")","");
        a[1] = a[1].replace(":","");
	    int[] num = new int[2];
	    num[0] = Integer.valueOf(a[0]);
        num[1] = Integer.valueOf(a[1]);
	    return num;
    }

    public static HashMap<String,int[]> getTimeTable(Sign_in_Time sign_in_time){

       HashMap<String,int[]> hash = new HashMap<>();
       int[] a = get(sign_in_time.getMon_in());
       hash.put("mon_in",a);
       a = get(sign_in_time.getMon_out());
       hash.put("mon_out",a);
        a = get(sign_in_time.getAft_in());
        hash.put("aft_in",a);
        a = get(sign_in_time.getAft_out());
        hash.put("aft_out",a);
        a = get(sign_in_time.getEve_in());
        hash.put("eve_in",a);
        a = get(sign_in_time.getEve_out());
        hash.put("eve_out",a);
        return hash;
    }

    public static int getMonth(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal.get(Calendar.MONTH)+1;
    }
    public static int getDay(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
       return  cal.get(Calendar.DAY_OF_MONTH);
    }


    public static List<String> getWeekDays(String date, String dateFormat, boolean isChina) throws ParseException {
        List<String> list  = new ArrayList<String>();
        Calendar c = Calendar.getInstance(Locale.CHINA);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        c.setTime(sdf.parse(date));
        int currentYear=c.get(Calendar.YEAR);
        int weekIndex = c.get(Calendar.WEEK_OF_YEAR);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==1&&isChina){
            c.add(Calendar.DAY_OF_MONTH,-1);
            String date_str = sdf.format(c.getTime());
            list=getWeekDays(date_str,dateFormat,isChina);
        }
        else{
            c.setWeekDate(currentYear, weekIndex, 1);
            for(int i=1;i<=7;i++){
                c.add(Calendar.DATE, 1);
                String date_str = sdf.format(c.getTime());
                list.add(date_str);
            }
        }
        return list;
    }
}
