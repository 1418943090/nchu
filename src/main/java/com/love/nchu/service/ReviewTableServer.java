package com.love.nchu.service;

import com.love.nchu.demain.ReviewTable;

import java.util.List;

public interface ReviewTableServer {

    void save(ReviewTable reviewTable);
    List<ReviewTable> getAll();

    int delReviewTableByUsername(String username);
}
