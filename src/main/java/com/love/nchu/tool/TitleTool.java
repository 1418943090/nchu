package com.love.nchu.tool;

import com.love.nchu.demain.TitleEdit;
import com.love.nchu.service.TitleEditServer;

public class TitleTool {

    public static TitleEdit titleEdit;

    protected  static void saveTitle(TitleEditServer titleEditServer){

        titleEditServer.save(new TitleEdit("南昌航空大学","南昌航空大学","软件学院","TDN之家"));
    }
    public static TitleEdit getTitle(TitleEditServer titleEditServer){

        if(titleEdit==null){
            if(titleEditServer.count()<1){
                saveTitle(titleEditServer);
            }
            titleEdit = titleEditServer.getTitle(1);
        }
         return titleEdit;
    }
}
