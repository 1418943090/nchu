package com.love.nchu.controller;

import com.love.nchu.demain.Paper;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.service.PaperServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.vo.deletePaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PersonCenterController {

    @Autowired
    PaperServer paperServer;

    @Value("${spring.papers.path}")
    String paperPath;
    String nowuser;
    @Autowired
    private UserInfoServer userInfoServer;
    @GetMapping("/basic_information/{username}")
    public ModelAndView basic_Information(@PathVariable String username, Model model){
        UserInfo userInfo = userInfoServer.getUserByUsername(username);
        System.out.println(userInfo);
        if(userInfo.getSex().equals("man")){
            userInfo.setSex("男");
        }
        else userInfo.setSex("女");

        userInfo.setAge(get_Age(userInfo.getBirthDate()));

        model.addAttribute("user_info",userInfo);
        return new ModelAndView("basic_information","basic_information",model);
    }
    @GetMapping("/papers/{username}")
    public ModelAndView paper(@PathVariable String username, Model model)
    {
        nowuser = username;
        List<Paper> list = paperServer.findPaperByUsername(username);
        model.addAttribute("list",list);
        return new ModelAndView("papers","model",model);
    }
    @PostMapping("/upload/paper")
    public void upload_paper(Model model, Paper paper, MultipartFile file){
        System.out.println(paperPath);
        System.out.println(paper);
        paper.setDate(new Date());
        paper.setUsername(nowuser);
        paper.setPath("/papers/"+nowuser+"/"+file.getOriginalFilename());
        System.out.println(paper);
        savePaper(file);
        paperServer.save(paper);
        //return new ModelAndView("papers","paper",model);
    }
    private void savePaper(MultipartFile file){
        File f = new File(paperPath+nowuser+"/");
        if(!f.exists()){
            f.mkdir();
        }
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(paperPath + nowuser+"/" + file.getOriginalFilename())))) {
            bos.write(file.getBytes());
            bos.flush();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public int get_Age(Date birthDate){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int birthYear =Integer.parseInt(birthDate.toString().substring(0,4));
        int nowYear = Integer.parseInt((sdf.format(d)).toString().substring(0,4));
        return nowYear-birthYear;
    }
}
