package com.love.nchu.service.impl;

import com.love.nchu.demain.UserInfo;
import com.love.nchu.repository.UserInfoRepository;
import com.love.nchu.service.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServerImpl implements UserInfoServer {

    @Autowired
    private UserInfoRepository userinfoRepository;


    @Override
    public UserInfo getUserByUsername(String username) {
        return userinfoRepository.findUserInfoByUsername(username);
    }

    @Override
    public UserInfo getUserByTel(String tel) {
        return userinfoRepository.findUserInfoByTel(tel);
    }

    @Override
    public UserInfo getUserByEmail(String email) {
        return userinfoRepository.findUserInfoByEmail(email);
    }

    @Override
    public void save(UserInfo userinfo) {
         userinfoRepository.save(userinfo);
    }
}
