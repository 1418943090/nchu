package com.love.nchu.service;

import com.love.nchu.demain.Sign_in_Time;

public interface Sign_in_TimeServer {

    void save(Sign_in_Time sign_in_time);

    Sign_in_Time getTimeBySeason(String seasonm);
    long count();
}
