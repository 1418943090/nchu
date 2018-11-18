package com.love.nchu.service;

import com.love.nchu.demain.Paper;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import org.springframework.stereotype.Service;


public interface UserServer {
    User findUserByUsername(String username);
    void save(User user);

}
