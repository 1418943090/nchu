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
    public void save(Paper paper) {
        paperRepository.save(paper);
    }

    @Override
    public List<Paper> findPaperByUsername(String username) {
        return paperRepository.findPaperByUsername(username);
    }
}
