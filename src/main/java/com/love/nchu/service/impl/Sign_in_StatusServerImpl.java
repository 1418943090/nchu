package com.love.nchu.service.impl;

import com.love.nchu.demain.Sign_in_Status;
import com.love.nchu.repository.Sign_in_StatusRepository;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.vo.weekShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class Sign_in_StatusServerImpl implements Sign_in_StatusServer {

    @Autowired
    Sign_in_StatusRepository sign_in_statusRepository;
    @Override
    public Sign_in_Status getSign_in_StatusByUsernaemAndDate(String username, String date) {
        return sign_in_statusRepository.findSign_in_StatusByUsernameAndDateEquals(username,date);
    }

    @Override
    public void save(Sign_in_Status sign_in_status) {
        sign_in_statusRepository.save(sign_in_status);
    }

    @Override
    public int signin(String time, String username, String date) {
        return sign_in_statusRepository.singin(time,username,date);
    }

    @Override
    public List<Sign_in_Status> getSign_in_StatusByDate(String date) {
        return sign_in_statusRepository.getSign_in_StatusByDate(date);
    }

//    @Override
//    public List<weekShow> getWeekShow(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
//        return sign_in_statusRepository.getWeekShow(monday,tuesday,wednesday,thursday,friday,saturday,sunday);
//    }
}
