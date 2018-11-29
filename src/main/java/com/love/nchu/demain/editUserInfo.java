package com.love.nchu.demain;

import org.springframework.stereotype.Component;

@Component
public class editUserInfo {

    private String editUsername;
    private String editSchool;
    private String editEmail;
    private String editTel;
    private String editIdentity;
    private String editResearch_Direct;
    private String editSelf_Introduction;
    protected editUserInfo(){

    }

    public editUserInfo(String editUsername, String editSchool, String editEmail, String editTel, String editIdentity, String editResearch_Direct, String editSelf_Introduction) {
        this.editUsername = editUsername;
        this.editSchool = editSchool;
        this.editEmail = editEmail;
        this.editTel = editTel;
        this.editIdentity = editIdentity;
        this.editResearch_Direct = editResearch_Direct;
        this.editSelf_Introduction = editSelf_Introduction;
    }

    public String getEditUsername() {
        return editUsername;
    }

    public void setEditUsername(String editUsername) {
        this.editUsername = editUsername;
    }

    public String getEditSchool() {
        return editSchool;
    }

    public void setEditSchool(String editSchool) {
        this.editSchool = editSchool;
    }

    public String getEditEmail() {
        return editEmail;
    }

    public void setEditEmail(String editEmail) {
        this.editEmail = editEmail;
    }

    public String getEditTel() {
        return editTel;
    }

    public void setEditTel(String editTel) {
        this.editTel = editTel;
    }

    public String getEditIdentity() {
        return editIdentity;
    }

    public void setEditIdentity(String editIdentity) {
        this.editIdentity = editIdentity;
    }

    public String getEditResearch_Direct() {
        return editResearch_Direct;
    }

    public void setEditResearch_Direct(String editResearch_Direct) {
        this.editResearch_Direct = editResearch_Direct;
    }

    public String getEditSelf_Introduction() {
        return editSelf_Introduction;
    }

    public void setEditSelf_Introduction(String editSelf_Introduction) {
        this.editSelf_Introduction = editSelf_Introduction;
    }

    @Override
    public String toString() {
        return "editUserInfo{" +
                "editUsername='" + editUsername + '\'' +
                ", editSchool='" + editSchool + '\'' +
                ", editEmail='" + editEmail + '\'' +
                ", editTel='" + editTel + '\'' +
                ", editIdentity='" + editIdentity + '\'' +
                ", editResearch_Direct='" + editResearch_Direct + '\'' +
                ", editSelf_Introduction='" + editSelf_Introduction + '\'' +
                '}';
    }
}
