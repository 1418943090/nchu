package com.love.nchu.repository;

import com.love.nchu.demain.Sign_in_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface Sign_in_StatusRepository extends JpaRepository<Sign_in_Status,String> {

    @Query("select t from  Sign_in_Status  t where t.username=?1 and t.date=?2")
    Sign_in_Status findSign_in_StatusByUsernameAndDateEquals(String username, String date);

    @Modifying
    @Query("update Sign_in_Status t set t=?1 where t.username=?2 and t.date=?3 ")
    int singin(String time,String username,String date);
}
