//package com.love.nchu.controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileInputStream;
//@RestController
//public class PdfController {
//    @RequestMapping(value = "/preview", method = RequestMethod.GET)
//    public void pdfStreamHandler(String fileName, HttpServletRequest request, HttpServletResponse response) {
//        File file = new File("D:/temp/test01/0/"+fileName);
//        if (file.exists()){
//            byte[] data = null;
//            try {
//                FileInputStream input = new FileInputStream(file);
//                data = new byte[input.available()];
//                input.read(data);
//                response.getOutputStream().write(data);
//                input.close();
//            } catch (Exception e) {
//                //logger.error("pdf文件处理异常：" + e.getMessage());
//            }
//        }else{
//            return;
//        }
//    }
//}
