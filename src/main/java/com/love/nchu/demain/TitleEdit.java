package com.love.nchu.demain;

import javax.persistence.*;

@Entity
@Table
public class TitleEdit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private int id;
    private String title1;
    private String title2;
    private String title3;
    private String title4;
    protected TitleEdit(){

    }
    public TitleEdit(String title1, String title2, String title3, String title4) {
        this.title1 = title1;
        this.title2 = title2;
        this.title3 = title3;
        this.title4 = title4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle1() {
        if(title1==null)
            return "";
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        if(title2==null)
            return "";
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        if(title3==null)
            return "";
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getTitle4() {
        if(title4==null)
            return "";
        return title4;
    }

    public void setTitle4(String title4) {
        this.title4 = title4;
    }

    @Override
    public String toString() {
        return "TitleEdit{" +
                "id=" + id +
                ", title1='" + title1 + '\'' +
                ", title2='" + title2 + '\'' +
                ", title3='" + title3 + '\'' +
                ", title4='" + title4 + '\'' +
                '}';
    }
}
