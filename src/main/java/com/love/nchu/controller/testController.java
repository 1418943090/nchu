//package com.love.nchu.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//
//@RestController
//public class testController {
//
//    @Value("${spring.img.ab.path}")
//    String staticLocations;
//
//
//
//    @RequestMapping("/test")
//    public void test() throws Exception{
//
//        System.out.println(staticLocations);
////          File file = new File(staticpath+File.separator+"img");
////          if(!file.exists()){
////              file.mkdirs();
////          }
//
//
////        System.out.println(staticLocations);
////
////        File newfile = new File(staticLocations+"img");
////        if(!newfile.exists())
////        {
////            System.out.println("AAAA");
////            newfile.mkdirs();
////        }
////
////        File path = new File(ResourceUtils.getURL("classpath:").getPath());
////        System.out.println(path.getParent());
////        File f = new File(path.getParent()+File.separator+"")
////        System.out.print(path);
//    }
//
//}
