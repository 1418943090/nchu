package com.love.nchu.demain;


import javax.persistence.*;

@Entity
@Table(name="sign_in_time")
public class Sign_in_Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mon_in;
    private String mon_out;

    private String aft_in;
    private String aft_out;

    private String eve_in;
    private String eve_out;

    private String date_start;
    private String date_end;

    private String season;

    protected Sign_in_Time(){
    }

    public Sign_in_Time(String mon_in, String mon_out, String aft_in, String aft_out, String eve_in, String eve_out, String date_start, String date_end, String season) {
        this.mon_in = mon_in;
        this.mon_out = mon_out;
        this.aft_in = aft_in;
        this.aft_out = aft_out;
        this.eve_in = eve_in;
        this.eve_out = eve_out;
        this.date_start = date_start;
        this.date_end = date_end;
        this.season = season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMon_in() {
        return mon_in;
    }

    public void setMon_in(String mon_in) {
        this.mon_in = mon_in;
    }

    public String getMon_out() {
        return mon_out;
    }

    public void setMon_out(String mon_out) {
        this.mon_out = mon_out;
    }

    public String getAft_in() {
        return aft_in;
    }

    public void setAft_in(String aft_in) {
        this.aft_in = aft_in;
    }

    public String getAft_out() {
        return aft_out;
    }

    public void setAft_out(String aft_out) {
        this.aft_out = aft_out;
    }

    public String getEve_in() {
        return eve_in;
    }

    public void setEve_in(String eve_in) {
        this.eve_in = eve_in;
    }

    public String getEve_out() {
        return eve_out;
    }

    public void setEve_out(String eve_out) {
        this.eve_out = eve_out;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return "Sign_in_Time{" +
                "id=" + id +
                ", mon_in='" + mon_in + '\'' +
                ", mon_out='" + mon_out + '\'' +
                ", aft_in='" + aft_in + '\'' +
                ", aft_out='" + aft_out + '\'' +
                ", eve_in='" + eve_in + '\'' +
                ", eve_out='" + eve_out + '\'' +
                ", date_start='" + date_start + '\'' +
                ", date_end='" + date_end + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
