package com.love.nchu.vo;

import java.io.Serializable;



public class weekShow implements Serializable {

    String username;
    int monday;
    int tuesday;
    int wednesday;
    int thursday;
    int friday;
    int saturday;
    int sunday;
    int count;
    protected weekShow(){

    }

    public weekShow(String username, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, int sunday) {
        this.username = username;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public int count(){
        return this.monday+this.tuesday+this.thursday+this.friday+this.saturday+this.sunday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMonday() {
        return monday;
    }

    public void setMonday(int monday) {
        this.monday = monday;
    }

    public int getTuesday() {
        return tuesday;
    }

    public void setTuesday(int tuesday) {
        this.tuesday = tuesday;
    }

    public int getWednesday() {
        return wednesday;
    }

    public void setWednesday(int wednesday) {
        this.wednesday = wednesday;
    }

    public int getThursday() {
        return thursday;
    }

    public void setThursday(int thursday) {
        this.thursday = thursday;
    }

    public int getFriday() {
        return friday;
    }

    public void setFriday(int friday) {
        this.friday = friday;
    }

    public int getSaturday() {
        return saturday;
    }

    public void setSaturday(int saturday) {
        this.saturday = saturday;
    }

    public int getSunday() {
        return sunday;
    }

    public void setSunday(int sunday) {
        this.sunday = sunday;
    }


    public int getCount() {
        return monday+tuesday+wednesday+thursday+friday+saturday+sunday;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "weekShow{" +
                "username='" + username + '\'' +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", Wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                '}';
    }
}
