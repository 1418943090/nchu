package com.love.nchu.service;

import com.love.nchu.demain.User;


public interface UserServer {
    User findUserByUsername(String username);
    void save(User user);
    int delUserByUsername(String username);

}
