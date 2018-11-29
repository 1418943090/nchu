package com.love.nchu.service.impl;

import com.love.nchu.demain.UserInfo;
import com.love.nchu.demain.editUserInfo;
import com.love.nchu.repository.UserInfoRepository;
import com.love.nchu.service.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServerImpl implements UserInfoServer {

    @Autowired
    private UserInfoRepository userinfoRepository;


   // @Cacheable(key="#username",value = "userinfo")
    @Override
    public UserInfo getUserByUsername(String username) {
        return userinfoRepository.findUserInfoByUsername(username);
    }

   // @Cacheable(key="#tel",value = "userinfo")
    @Override
    public UserInfo getUserByTel(String tel) {
        return userinfoRepository.findUserInfoByTel(tel);
    }

   // @Cacheable(key="#email",value = "userinfo")
    @Override
    public UserInfo getUserByEmail(String email) {
        return userinfoRepository.findUserInfoByEmail(email);
    }


    @Override
    public void save(UserInfo userinfo) {
         userinfoRepository.save(userinfo);
    }

    @Override
    public String isUserExistByUsername(String username) {
        return userinfoRepository.isExistUserByUsername(username);
    }

    @Override
    public String isUserExistByTel(String tel) {
        return userinfoRepository.isExistUserByTel(tel);
    }

    @Override
    public String isUserExistByEmail(String email) {
        return userinfoRepository.isExistUserByEmail(email);
    }


    @Override
    public int updateUserInfo(editUserInfo editUserInfo) {
      return   userinfoRepository.updateUserInfo(editUserInfo);
    }

    @Override
    public String getNameByUsername(String username) {
        return userinfoRepository.getNameByUsername(username);
    }

    @Override
    public List<UserInfo> getUserInfoByIdentity(String identity) {
        return userinfoRepository.findUserInfoByIdentity(identity);
    }

    @Override
    public List<String> getAllStudentUsername() {
        return userinfoRepository.getAllStudentUsername();
    }

    @Override
    public List<UserInfo> getAll() {
        return userinfoRepository.findAll();
    }

    @Override
    public int delUserInfoByUsername(String username) {
        return userinfoRepository.deleteUserInfoByUsername(username);
    }
}
