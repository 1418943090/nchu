package com.love.nchu.demain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "reviewtable")
public class ReviewTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
    private String username;
    private Date date;
    private String status;
    private String ending;
    private String cla;

    protected ReviewTable(){

    }

    public ReviewTable(String username, Date date, String status, String ending, String cla) {
        this.username = username;
        this.date = date;
        this.status = status;
        this.ending = ending;
        this.cla = cla;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    @Override
    public String toString() {
        return "ReviewTable{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", ending='" + ending + '\'' +
                ", cla='" + cla + '\'' +
                '}';
    }
}
