package com.love.nchu.service.impl;

import com.love.nchu.demain.Paper;
import com.love.nchu.repository.PaperRepository;
import com.love.nchu.service.PaperServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class PaperServerImpl implements PaperServer {

    @Autowired
    PaperRepository paperRepository;

    @Override
    public void deletePaper(Integer id) {
        paperRepository.deletePaperById(id);
    }

    @Override
    public void updatePaper(Integer id, String title) {
        paperRepository.updatePaper(id,title);
    }

    @Override
    public List<Paper> getAllPapers() {
        return paperRepository.getAllByIdIsNotNull();
    }
    @Override
    public void save(Paper paper) {
        paperRepository.save(paper);
    }


    @Override
    public List<Paper> findPaperByUsername(String username) {
        return paperRepository.findPaperByUsername(username);
    }

    @Override
    public List<Paper> getPaperByNameLikeOrTitleLike(String value) {
        System.out.println(value);
        return paperRepository.findPaperByNameLikeOrTitleLike(value);
    }

    @Override
    public List<Paper> getPaperByNameLike(String name) {
        System.out.println(name);
        return paperRepository.findPaperByNameLike(name);
    }

    @Override
    public List<Paper> getPaperByTitleLike(String title) {
        System.out.println(title);
        return paperRepository.findPaperByTitleLike(title);
    }
}
