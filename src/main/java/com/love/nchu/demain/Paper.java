package com.love.nchu.demain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Paper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
    private String title;
    private String path;
    private String username;
    private Date  date;

    protected Paper(){

    }

    public Paper(String title, String path, String username, Date date) {
        this.title = title;
        this.path = path;
        this.username = username;
        this.date = date;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    @Override
    public String toString() {
        return "Paper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }
}
