package com.love.nchu.repository;

import com.love.nchu.demain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User,String> {


    User findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("delete from User u where u.username=?1")
    int deleteUserByUsername(String username);


}
