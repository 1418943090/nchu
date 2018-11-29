package com.love.nchu.demain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ErrorVo implements Serializable {
    private String data;
    protected ErrorVo(){

    }

    public ErrorVo(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Error{" +
                "data='" + data + '\'' +
                '}';
    }
}
