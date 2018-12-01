package com.love.nchu.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class PerVo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    String username;
    String regisitory_date;
    boolean accountNonLocked;
    String cla;

    public PerVo(){

    }

    public PerVo(String username, String regisitory_date, boolean accountNonLocked) {
        this.username = username;
        this.regisitory_date = regisitory_date;
        this.accountNonLocked = accountNonLocked;
    }

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

    public String getRegisitory_date() {
        return regisitory_date;
    }

    public void setRegisitory_date(String registry_date) {
        this.regisitory_date = regisitory_date;
    }

    public String isAccountNonLocked() {
        if(accountNonLocked==true)
            return "锁定";
        else return "正常";
    }

    public void setAccountNonLocked(boolean account_non_locked) {
        this.accountNonLocked = account_non_locked;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    @Override
    public String toString() {
        return "PerVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", regisitory_date='" + regisitory_date + '\'' +
                ", account_non_locked=" + accountNonLocked +
                ", cla='" + cla + '\'' +
                '}';
    }
}
