package com.love.nchu.demain;

import javax.persistence.*;

@Entity
@Table(name="sign_in_status")
public class Sign_in_Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String date;
    private String mon_in;
    private String mon_out;
    private String aft_in;
    private String aft_out;
    private String eve_in;
    private String eve_out;
    //private int count= 6;

    protected  Sign_in_Status(){

    }

    public Sign_in_Status(String username,String date) {
        this.username = username;
        this.date = date;
    }

    public Sign_in_Status(String username, String date, String mon_in, String mon_out, String aft_in, String aft_out, String eve_in, String eve_out) {
        this.username = username;
        this.date = date;
        this.mon_in = mon_in;
        this.mon_out = mon_out;
        this.aft_in = aft_in;
        this.aft_out = aft_out;
        this.eve_in = eve_in;
        this.eve_out = eve_out;
    }

    public int getCount(){

        int num = 0;
        if(mon_in==null)
            num++;
        if(mon_out==null)
            num++;
        if(aft_in==null)
            num++;
        if(aft_out==null)
            num++;
        if(eve_in==null)
            num++;
        if(eve_out==null)
            num++;
        return num;
    }


//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMon_in() {
        if(mon_in==null)
            return "未签到";
        return mon_in;
    }

    public void setMon_in(String mon_in) {
        this.mon_in = mon_in;
    }

    public String getMon_out() {
        if(mon_out==null)
            return "未签到";
        return mon_out;
    }


    public String getMon_in_class(){
        if(mon_in==null)
            return "danger";
        else  return "success";
    }
    public String getMon_out_class(){
        if(mon_out==null)
            return "danger";
        else  return "success";
    }

    public String getAft_in_class(){
        if(aft_in==null)
            return "danger";
        else  return "success";
    }

    public String getAft_out_class(){
        if(aft_out==null)
            return "danger";
        else  return "success";
    }
    public String getEve_in_class(){
        if(eve_in==null)
            return "danger";
        else  return "success";
    }
    public String getEve_out_class(){
        if(eve_out==null)
            return "danger";
        else  return "success";
    }
    public void setMon_out(String mon_out) {
        this.mon_out = mon_out;
    }

    public String getAft_in() {
        if(aft_in==null)
            return "未签到";
        return aft_in;
    }

    public void setAft_in(String aft_in) {
        this.aft_in = aft_in;
    }

    public String getAft_out() {
        if(aft_out==null)
            return "未签到";
        return aft_out;
    }

    public void setAft_out(String aft_out) {
        this.aft_out = aft_out;
    }

    public String getEve_in() {
        if(eve_in==null)
            return "未签到";
        return eve_in;
    }

    public void setEve_in(String eve_in) {
        this.eve_in = eve_in;
    }

    public String getEve_out() {
        if(eve_out==null)
            return "未签到";
        return eve_out;
    }

    public void setEve_out(String eve_out) {
        this.eve_out = eve_out;
    }


    @Override
    public String toString() {
        return "Sign_in_Status{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", mon_in='" + mon_in + '\'' +
                ", mon_out='" + mon_out + '\'' +
                ", aft_in='" + aft_in + '\'' +
                ", aft_out='" + aft_out + '\'' +
                ", eve_in='" + eve_in + '\'' +
                ", eve_out='" + eve_out + '\'' +
               // ", count=" + count +
                '}';
    }
}
