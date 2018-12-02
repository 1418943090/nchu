package com.love.nchu.service.impl;

import com.love.nchu.demain.TitleEdit;
import com.love.nchu.repository.TitleEditRepository;
import com.love.nchu.service.TitleEditServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TitleEditServerImpl implements TitleEditServer {
    @Autowired
    TitleEditRepository titleEditRepository;

    @Override
    public void save(TitleEdit titleedit) {
          titleEditRepository.save(titleedit);
    }

    @Override
    public TitleEdit getTitle(int id) {
        return titleEditRepository.getOne(id);
    }


    public long count(){
      return titleEditRepository.count();
    }
}
