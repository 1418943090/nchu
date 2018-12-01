package com.love.nchu.vo;

import java.io.Serializable;

public class userTem implements Serializable {

    String username;
    String firstpassword;
    String secondpassword;
    String email;
    String vcode;

    protected userTem(){

    }

    public userTem(String username, String firstpassword, String secondpassword, String email, String vcode) {
        this.username = username;
        this.firstpassword = firstpassword;
        this.secondpassword = secondpassword;
        this.email = email;
        this.vcode = vcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstpassword() {
        return firstpassword;
    }

    public void setFirstpassword(String firstpassword) {
        this.firstpassword = firstpassword;
    }

    public String getSecondpassword() {
        return secondpassword;
    }

    public void setSecondpassword(String secondpassword) {
        this.secondpassword = secondpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @Override
    public String toString() {
        return "userTem{" +
                "username='" + username + '\'' +
                ", firstpassword='" + firstpassword + '\'' +
                ", secondpassword='" + secondpassword + '\'' +
                ", email='" + email + '\'' +
                ", vcode='" + vcode + '\'' +
                '}';
    }
}
