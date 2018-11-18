package com.love.nchu.controller;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RegistryController {
    @Autowired
    UserInfoServer userInfoServer;
    @Autowired
    UserServer userServer;

    static String imgPath = "src/main/resources/static/img/people/";

    @RequestMapping(value="/registry",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView registry(){
        return new ModelAndView("registry");
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping(value="/registry_action",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView registry_action(@Valid  UserInfo userInfo,
                                        BindingResult errors, Model model,
                                        @RequestParam(name="file",required = false) MultipartFile file
                                       // @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
    )throws Exception{
        //System.out.println(userInfo.getFirstpassword());
        field_init(model,userInfo);
       if(!iserror(errors,model,file,userInfo)){//校验输入是否正确
           if(Save_Image(file,userInfo)){//存储图像到目录中，把路径存到数据库中
              // System.out.print(userInfo.toString());
               userInfoServer.save(userInfo);//存储用户详细信息
               userServer.save(new User( userInfo.getUsername(),SHAencrypt.encryptSHA(userInfo.getFirstpassword()),userInfo.getIdentity(),true,true,true,true));
             //  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
               //存储用户名密码
              // userServer.save(new User(userInfo.getUsername(),passwordEncoder.encode(userInfo.getFirstpassword()),userInfo.getIdentity(),true,true,true,true));
               return new ModelAndView("registry_success");
           }
       }
        return new ModelAndView("registry","registry",model);
    }
       public  void  field_init(Model model,UserInfo userInfo){
           model.addAttribute("username_value",userInfo.getUsername());
           model.addAttribute("username_status","");
           model.addAttribute("firstpassword_value",userInfo.getFirstpassword());
           model.addAttribute("secondpassword_value",userInfo.getSecondpassword());
           if(userInfo.getBirthDate()!=null){
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(userInfo.getBirthDate());
               model.addAttribute("birthDate_value",dateString);
               System.out.println(dateString);
           }else   model.addAttribute("birthDate_value","");

           model.addAttribute("birthDate_status","");
           model.addAttribute("email_value",userInfo.getEmail());
           model.addAttribute("email_status","");
           model.addAttribute("name_value",userInfo.getName());
           model.addAttribute("name_status","");
           model.addAttribute("sex_value",userInfo.getSex());
           model.addAttribute("sex_status","");
           model.addAttribute("tel_value",userInfo.getTel());
           model.addAttribute("tel_status","");
           model.addAttribute("birthplace_value",userInfo.getBirthplace());
           model.addAttribute("birthplace_status","");
           model.addAttribute("school_value",userInfo.getSchool());
           model.addAttribute("school_status","");
           model.addAttribute("identity_value",userInfo.getIdentity());
           model.addAttribute("identity_status","");
           model.addAttribute("research_direct_value",userInfo.getResearch_direct());
           model.addAttribute("research_direct_status","");
           model.addAttribute("picture_status","");
           model.addAttribute("self_introduction_value",userInfo.getSelf_introduction());
           model.addAttribute("self_introduction__status","");
       }
       public  boolean iserror(BindingResult errors,Model model,MultipartFile file,UserInfo userInfo){
           boolean iserror = false;
           if(errors.hasErrors()){
               iserror =true;
               errors.getAllErrors().stream().forEach(error->{
                   FieldError fieldError = (FieldError)error;
                   model.addAttribute(fieldError.getField()+"_status",fieldError.getDefaultMessage());
               });
           }
           if(file.isEmpty()){
               model.addAttribute("file_status","请选择你的个人头像");
               iserror = true;
           }
           else if(!model.containsAttribute("firstpassword_status") && !model.containsAttribute("secondpassword_status")){
               if(!userInfo.getFirstpassword().equals(userInfo.getSecondpassword())){
                   model.addAttribute("firstpassword_status","两次密码不一致");
                   model.addAttribute("secondpassword_status","两次密码不一致");
                    iserror = true;
               }
           }
           if(userInfoServer.getUserByUsername(userInfo.getUsername())!=null){
               model.addAttribute("username_status","该用户名已被注册了");
                iserror = true;
           }else if(userInfoServer.getUserByEmail(userInfo.getEmail())!=null){
               model.addAttribute("email_status","该邮箱被注册了");
                iserror = true;
           }else if(userInfoServer.getUserByTel(userInfo.getTel())!=null){
               model.addAttribute("tel_status","该号码已被注册了");
                iserror = true;
           }
           return iserror;
       }
       public boolean Save_Image(MultipartFile file,UserInfo userInfo){
         System.out.println(userInfo.toString());
           try {
               BufferedOutputStream out = new BufferedOutputStream(
                       new FileOutputStream(new File(imgPath + userInfo.getUsername()+".jpg")));//保存图片到目录下
               out.write(file.getBytes());
               out.flush();
               out.close();
               String filename = "/img/people/" + userInfo.getUsername() + ".jpg";
              userInfo.setPicture(filename);
           } catch (FileNotFoundException e) {
               e.printStackTrace();
              return false;
           } catch (IOException e) {
               e.printStackTrace();
               return false;
           }
        return true;
       }
}
