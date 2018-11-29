package com.love.nchu.repository;

import com.love.nchu.demain.Sign_in_Time;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Sign_in_TimeRepository extends JpaRepository<Sign_in_Time,String> {




    @Query("select  t from Sign_in_Time t where season=?1")
  Sign_in_Time findBySeason(String season);

}
