package com.love.nchu.service.impl;

import com.love.nchu.demain.User;
import com.love.nchu.repository.UserRepository;
import com.love.nchu.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserServer {

    @Autowired
    UserRepository userRepository;
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Override
    public void save(User user) {
       userRepository.save(user);
    }
}
