package com.love.nchu.repository;

import com.love.nchu.demain.ReviewTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReviewTableRepository extends JpaRepository<ReviewTable,Integer> {


    @Transactional
    @Modifying
    @Query("delete from ReviewTable  r where r.username=?1")
    int deleteReviewTableByUsername(String username);

}
