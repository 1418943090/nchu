package com.love.nchu.repository;

import com.love.nchu.demain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    UserInfo findUserInfoByUsername(String username);
    UserInfo findUserInfoByTel(String tel);
    UserInfo findUserInfoByEmail(String email);


}
