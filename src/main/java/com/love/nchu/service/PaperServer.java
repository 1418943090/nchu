package com.love.nchu.service;

import com.love.nchu.demain.Paper;

import java.util.List;

public interface PaperServer {

    List<Paper> findPaperByUsername(String username);
    void save(Paper paper);
    void deletePaper(Integer id);
    void updatePaper(Integer id,String title);
    List<Paper> getAllPapers();

    List<Paper> getPaperByNameLikeOrTitleLike(String value);
    List<Paper> getPaperByNameLike(String name);
    List<Paper> getPaperByTitleLike(String title);

}
