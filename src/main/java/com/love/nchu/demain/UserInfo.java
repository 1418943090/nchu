package com.love.nchu.demain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_info")
public class UserInfo  implements Serializable {
    @Id
    private String username;

    @Transient
    private String password;

    private int age;
    private Date  birthDate;
    private String email;
    private String name;
    private String sex;
    private String tel;
    private String birthplace;
    private String school;
    private String identity;
    private String teachername;
    private String research_direct;
    private String picture;
    @Column(length = 400)
    private String self_introduction;
    private String registry_date;
    protected UserInfo(){

    }
    public UserInfo(String username, String email, String name, String sex, String tel, String birthplace, String school, String identity,String teachername, String research_direct, String picture, String self_introduction) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
        this.birthplace = birthplace;
        this.school = school;
        this.identity = identity;
        this.research_direct = research_direct;
        this.picture = picture;
        this.teachername = teachername;
        this.self_introduction = self_introduction;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getResearch_direct() {
        return research_direct;
    }

    public void setResearch_direct(String research_direct) {
        this.research_direct = research_direct;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSelf_introduction() {
        return self_introduction;
    }

    public void setSelf_introduction(String self_introduction) {
        this.self_introduction = self_introduction;
    }

    public String getRegistry_date() {
        return registry_date;
    }

    public void setRegistry_date(String registry_date) {
        this.registry_date = registry_date;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", school='" + school + '\'' +
                ", identity='" + identity + '\'' +
                ", teachername='" + teachername + '\'' +
                ", research_direct='" + research_direct + '\'' +
                ", picture='" + picture + '\'' +
                ", self_introduction='" + self_introduction + '\'' +
                ", registry_date='" + registry_date + '\'' +
                '}';
    }
}

