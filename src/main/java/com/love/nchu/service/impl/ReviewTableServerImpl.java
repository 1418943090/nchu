package com.love.nchu.service.impl;

import com.love.nchu.demain.ReviewTable;
import com.love.nchu.repository.ReviewTableRepository;
import com.love.nchu.service.ReviewTableServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewTableServerImpl implements ReviewTableServer {

    @Autowired
    ReviewTableRepository reviewTableRepository;
    @Override
    public void save(ReviewTable reviewTable) {
        reviewTableRepository.save(reviewTable);
    }

    @Override
    public List<ReviewTable> getAll() {
        return reviewTableRepository.findAll();
    }

    @Override
    public int delReviewTableByUsername(String username) {
        return reviewTableRepository.deleteReviewTableByUsername(username);
    }
}
