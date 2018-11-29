package com.love.nchu.service;

import com.love.nchu.demain.Sign_in_Status;

public interface Sign_in_StatusServer {


    Sign_in_Status getSign_in_StatusByUsernaemAndDate(String username, String date);
    void save(Sign_in_Status sign_in_status);
    int signin(String time,String username,String date);
}
