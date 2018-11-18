package com.love.nchu.repository;

import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


    User findUserByUsername(String username);


}
