package com.love.nchu.service;

import com.love.nchu.demain.TitleEdit;

public interface TitleEditServer {

      void save(TitleEdit titleedit);
      TitleEdit getTitle(int id);
      long count();
}
