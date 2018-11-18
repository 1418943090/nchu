package com.love.nchu.vo;

import java.io.Serializable;
import java.util.List;

public class deletePaperVo implements Serializable {

    private String userName;
    private List<Integer> listPaperId;

    protected deletePaperVo() {
    }

    public deletePaperVo(String userName, List<Integer> listPaperId) {
        this.userName = userName;
        this.listPaperId = listPaperId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Integer> getListPaperId() {
        return listPaperId;
    }

    public void setListPaperId(List<Integer> listPaperId) {
        this.listPaperId = listPaperId;
    }

    @Override
    public String toString() {
        return "deletePaperVo{" +
                "userName='" + userName + '\'' +
                ", listPaperId=" + listPaperId +
                '}';
    }
}
