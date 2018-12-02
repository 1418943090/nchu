package com.love.nchu.tool;

import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.vo.MyDate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RegistryTool {

  //是否已经填写了个人信息
   public static boolean isFillBasicInformation(UserInfoServer userInfoServer,String username){

       UserInfo userInfo = userInfoServer.getUserByUsername(username);
       if(userInfo==null){
           return false;
       }
       return true;
   }
  //用户名是否已经被注册
   public static boolean isUsernameExist(UserServer userServer,String username){

       User user = userServer.findUserByUsername(username);
       if(user==null){
           return false;
       }
       return true;
   }

   //邮箱是否已经被注册
   public static boolean isEmailExist(UserServer userServer,String email){

       User user = userServer.findUserByEmail(email);
       if(user==null){
           return false;
       }
       return true;
   }
   //校验码是否正确
   public static boolean codeVolidator(String code,String code2){

       if(code==null || code2==null){
           return false;
       }
       if( (code.toLowerCase().equals((code2.toLowerCase())))){
           return true;
       }
       return false;
   }

        public static int get_Age(Date birthDate){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(birthDate);
        int birthYear =Integer.parseInt(s.substring(0,4));
        int nowYear = Integer.parseInt(MyDate.getDate().substring(0,4));
        return nowYear-birthYear;
    }

   public static void saveUserInfo(UserInfoServer userInfoServer,UserServer userServer,UserInfo userInfo){

           userInfo.setRegistry_date(MyDate.getDate());
           userInfo.setAge(get_Age(userInfo.getBirthDate()));
           User user = userServer.findUserByUsername(userInfo.getUsername());
           userInfo.setEmail(user.getEmail());
           if(userInfo.getSex().equals("man"))
               userInfo.setSex("男");
           else userInfo.setSex("女");
           userInfoServer.save(userInfo);
   }

    public static boolean  Save_Image(MultipartFile file, UserInfo userInfo,String img_path,String img_vm_path){
         File newfile = new File(img_path);
         if(!newfile.exists()){
             newfile.mkdirs();
         }
         System.out.println(img_path);
         File bfile = new File(newfile,userInfo.getUsername()+".jpg");
         if(!bfile.exists()){
             try{
             bfile.createNewFile();
             }catch(IOException e){
                 e.printStackTrace();
             }
         }
           try {
               BufferedOutputStream out = new BufferedOutputStream(
                       new FileOutputStream(bfile));//保存图片到目录下
               out.write(file.getBytes());
               out.flush();
               out.close();
               String filename = img_vm_path + userInfo.getUsername() + ".jpg";
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

       public static boolean NewAndOldPasswordEqualsCheck(UserServer userServer,String username,String newPassword) throws  Exception{

       User user = userServer.findUserByUsername(username);
       if(user.getPassword().equals(SHAencrypt.encryptSHA(newPassword))){
           return true;
       }
       return false;

       }

}
