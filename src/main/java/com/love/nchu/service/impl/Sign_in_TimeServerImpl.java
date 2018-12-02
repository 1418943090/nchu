package com.love.nchu.service.impl;

import com.love.nchu.demain.Sign_in_Time;
import com.love.nchu.repository.Sign_in_TimeRepository;
import com.love.nchu.service.Sign_in_TimeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class Sign_in_TimeServerImpl implements Sign_in_TimeServer {
    @Override
    @Cacheable(value = "sign_in_time",key="#season")
    public Sign_in_Time getTimeBySeason(String season) {
        return sign_in_timeRepository.findBySeason(season);
    }

    @Autowired
    Sign_in_TimeRepository sign_in_timeRepository;

    @Override

    public void save(Sign_in_Time sign_in_time) {

        sign_in_timeRepository.save(sign_in_time);
    }

    public long count(){
        return sign_in_timeRepository.count();
    }
}
