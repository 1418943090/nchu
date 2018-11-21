package com.love.nchu.repository;

import com.love.nchu.demain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    UserInfo findUserInfoByUsername(String username);
    UserInfo findUserInfoByTel(String tel);
    UserInfo findUserInfoByEmail(String email);

    @Query("select username from UserInfo u where u.username = ?1")
    String isExistUserByUsername(String username);


    @Query("select username from UserInfo u where u.tel = ?1")
    String isExistUserByTel(String tel);


    @Query("select username from UserInfo u where u.email = ?1")
    String isExistUserByEmail(String email);


}
