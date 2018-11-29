package com.love.nchu.demain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="paper")
public class Paper implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
    private String title;
    @Column(name = "summary",length = 500)
    private String summary;
    private String path;
    private String username;
    private String name;
    private Date  date;

    protected Paper(){

    }

    public Paper(String title, String summary, String path, String username, String name, Date date) {
        this.title = title;
        this.summary = summary;
        this.path = path;
        this.username = username;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", path='" + path + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
