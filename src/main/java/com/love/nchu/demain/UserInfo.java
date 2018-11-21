package com.love.nchu.demain;


import org.hibernate.validator.constraints.Length;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="user_info")
public class UserInfo  implements Serializable {
    @Id
    private String username;

    private String firstpassword;

    private String secondpassword;
    private int age;
    private Date  birthDate;
    private String email;
    private String name;
    private String sex;
    private String tel;
    private String birthplace;
    private String school;
    private String identity;
    private String research_direct;
    private String picture;
    private String self_introduction;
    protected UserInfo(){

    }
    public UserInfo(String username, String email, String name, String sex, String tel, String birthplace, String school, String identity, String research_direct, String picture, String self_introduction) {
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
        this.self_introduction = self_introduction;
    }
    @NotBlank(message="请输入用户名")
    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}",message = "请检查用户名输入是否要求")
    public String getUsername(){return this.username;}
    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message="请输入密码")
    @Length(max=20,message = "密码长度超过20个字符")
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*$",
             message="密码安全强度不够")
    public String getFirstpassword() {
        return firstpassword;
    }
    public void setFirstpassword(String firstpassword) {
        this.firstpassword = firstpassword;
    }
    @NotBlank(message="请输入密码")
    @Length(max=20,message = "密码长度超过20个字符")
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*$",
            message="密码安全强度不够")
    public String getSecondpassword() {
        return secondpassword;
    }
    public void setSecondpassword(String secondpassword) {
        this.secondpassword = secondpassword;
    }
    @NotNull(message="请选择你的出生日期")
    @Past(message = "那个时间你还没出生哦")
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Transient
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Pattern(regexp = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$",message = "请检查邮箱是否输入正确")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message="请输入真实姓名")
    @Pattern(regexp = "[\u4e00-\u9fa5]{2,4}",message = "请输入正确的中文姓名")
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

    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",message = "请输入正确的电话号码")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @NotBlank(message = "请输入学校名称")
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
    @NotBlank(message = "请输入籍贯")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
    @NotBlank(message = "请输入研究方向")
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

    @NotBlank(message = "请输入自我简介")
    @Length(max=120,message = "简介字数超过限制了")
    public String getSelf_introduction() {
        return self_introduction;
    }

    public void setSelf_introduction(String self_introduction) {
        this.self_introduction = self_introduction;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", firstpassword='" + firstpassword + '\'' +
                ", secondpassword='" + secondpassword + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", school='" + school + '\'' +
                ", identity='" + identity + '\'' +
                ", research_direct='" + research_direct + '\'' +
                ", picture='" + picture + '\'' +
                ", self_introduction='" + self_introduction + '\'' +
                '}';
    }
}

