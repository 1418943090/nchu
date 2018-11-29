package com.love.nchu.service;

import com.love.nchu.demain.UserInfo;
import com.love.nchu.demain.editUserInfo;

import java.util.List;

public interface UserInfoServer  {
     UserInfo getUserByUsername(String username) ;
     UserInfo getUserByTel(String tel) ;
     UserInfo getUserByEmail(String email) ;
     void save(UserInfo userinfo);
     String isUserExistByUsername(String username);
     String isUserExistByTel(String tel);
     String isUserExistByEmail(String email);
     int   updateUserInfo(editUserInfo editUserInfo);
     String getNameByUsername(String username);
     List<UserInfo> getUserInfoByIdentity(String identity);
     List<String> getAllStudentUsername();
     List<UserInfo> getAll();
     int delUserInfoByUsername(String username);

}
