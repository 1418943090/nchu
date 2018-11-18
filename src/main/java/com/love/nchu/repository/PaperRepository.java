package com.love.nchu.repository;

import com.love.nchu.demain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PaperRepository extends JpaRepository<Paper,String> {

    List<Paper> findPaperByUsername(String usernaem);

    void deletePaperById(Integer id);
    @Modifying
    @Query("update Paper p set p.title=?2 where p.id = ?1")
    void updatePaper(int id,String title);
}
