package com.love.nchu.service;
import com.love.nchu.demain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserInfoServer  {
     UserInfo getUserByUsername(String username) ;
     UserInfo getUserByTel(String tel) ;
     UserInfo getUserByEmail(String email) ;
     void save(UserInfo userinfo);
}
