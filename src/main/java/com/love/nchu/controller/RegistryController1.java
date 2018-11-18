//package com.love.nchu.controller;
//
//import com.love.nchu.demain.User;
//import com.love.nchu.demain.UserInfo;
//import com.love.nchu.service.UserInfoServer;
//import com.love.nchu.service.UserServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.ui.Model;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//import javax.servlet.http.HttpServletRequest;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//@RestController
//@RequestMapping("/registry")
//public class RegistryController1 {
//
//    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//
//    @Autowired
//    private UserInfoServer userInfoServer;
//    @Autowired
//    private UserServer userServer;
//
// @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView registry(
//                                 Model model,
//                                 @RequestParam(name="username",required = false,defaultValue = "")String username,
//                                 @RequestParam(name="firstpassword",required = false,defaultValue = "")String firstpassword,
//                                 @RequestParam(name="secondpassword",required = false,defaultValue = "")String secondpassword,
//                                 @RequestParam(name="email",required = false,defaultValue = "") String email,
//                                 @RequestParam(name="name",required = false,defaultValue = "")String name,
//                                 @RequestParam(name="sex",required = false,defaultValue = "")String sex,
//                                 @RequestParam(name="tel",required = false,defaultValue = "")String tel,
//                                 @RequestParam(name="birthplace",required = false,defaultValue = "")String birthplace,
//                                 @RequestParam(name="school",required = false,defaultValue = "")String school,
//                                 @RequestParam(name="identity",required = false,defaultValue = "")String identity,
//                                 @RequestParam(name="research_direct",required = false,defaultValue = "")String research_direct,
//                                 @RequestParam(name = "picture",required = false) MultipartFile picture,
//                                 @RequestParam(name="self_introduction",required = false,defaultValue = "")String self_introduction,
//                                 @RequestParam(name = "flag",required = false,defaultValue = "false")String flag
//                                 ){
//     boolean error = false;
//     model.addAttribute("username_value",username);
//     model.addAttribute("username_status","");
//     model.addAttribute("firstpassword_value",firstpassword);
//     model.addAttribute("firstpassword_status","");
//     model.addAttribute("secondpassword_value",secondpassword);
//     model.addAttribute("secondpassword_status","");
//     model.addAttribute("email_value",email);
//     model.addAttribute("email_status","");
//     model.addAttribute("name_value",name);
//     model.addAttribute("name_status","");
//     model.addAttribute("sex_value",sex);
//     model.addAttribute("sex_status","");
//     model.addAttribute("tel_value",tel);
//     model.addAttribute("tel_status","");
//     model.addAttribute("birthplace_value",birthplace);
//     model.addAttribute("birthplace_status","");
//     model.addAttribute("school_value",school);
//     model.addAttribute("school_status","");
//     model.addAttribute("identity_value",identity);
//     model.addAttribute("identity_status","");
//     model.addAttribute("research_direct_value",research_direct);
//     model.addAttribute("research_direct_status","");
//     model.addAttribute("picture_status","");
//     model.addAttribute("self_introduction_value",self_introduction);
//     model.addAttribute("self_introduction__status","");
//
//     String loginFlag = "false";
//     String space=" ";
//     int username_mixSize =3;
//     int username_maxSize = 10;
//     int password_minsize = 6;
//     int password_maxsize  =20;
//     int chinasename_minsize =2;
//     int tel_length =11;
//
//
//     if(loginFlag.equals(flag))
//     {
//         return new ModelAndView("registry","registry",model);
//     }
//
//     if(username.isEmpty()||username.trim().length()==0){
//
//         model.addAttribute("username_status","请输入用户名");
//         model.addAttribute("username_value","");
//         error = true;
//     }else if(username.indexOf(space)!=-1){
//
//         model.addAttribute("username_status","用户名存在非法字符");
//         error=true;
//     }
//     else if(username.length()<username_mixSize || username.length()>username_maxSize){
//         model.addAttribute("username_status","用户名长度为3至10个字符");
//         error = true;
//     }
//     else if(firstpassword.isEmpty() || firstpassword.trim().length()==0){
//         model.addAttribute("firstpassword_status","请输入密码");
//         model.addAttribute("firstpassword_value","");
//         error = true;
//     }else if(firstpassword.indexOf(space)!=-1){
//         model.addAttribute("firstpassword_status","存在非法字符");
//         model.addAttribute("firstpassword_value","");
//         error = true;
//     }
//     else if(firstpassword.length()<password_minsize || firstpassword.length()>password_maxsize){
//         model.addAttribute("firstpassword_status","密码长度为6至20个字符");
//         error = true;
//     }
//     else if(secondpassword.isEmpty() || secondpassword.trim().length()==0){
//         model.addAttribute("secondpassword_status","请输入密码");
//         model.addAttribute("secondpassword_value","");
//         error = true;
//     }
//     else if(!secondpassword.equals(firstpassword)){
//         model.addAttribute("firstpassword_status","两次输入密码不一致");
//         model.addAttribute("secondpassword_status","两次输入密码不一致");
//         error = true;
//     }
//     else if(email.isEmpty()|| email.trim().length()==0){
//         model.addAttribute("email_status","请输入邮箱");
//         model.addAttribute("email_value","");
//         error = true;
//     }
//     else if(!Pattern.matches(REGEX_EMAIL,email)){
//         model.addAttribute("email_status","邮箱格式错误");
//         error = true;
//     }
//     else if(name.isEmpty()||name.trim().length()==0) {
//         model.addAttribute("name_status", "请输入姓名");
//         model.addAttribute("name_value","");
//         error = true;
//     }else if(isChineseStr(name) && (name.length()<chinasename_minsize || name.length()>4 ||name.indexOf(space)!=-1)){
//         model.addAttribute("name_status", "请输入正确的姓名");
//         error = true;
//     }else if(tel.isEmpty() || tel.trim().length()==0){
//         model.addAttribute("tel_status","请输入电话号码");
//         error = true;
//     }else if(tel.length()!=tel_length || tel.indexOf(space)!=-1){
//         model.addAttribute("tel_status","请输入合法的电话号码");
//         error = true;
//     }else if(birthplace.isEmpty()||(birthplace=birthplace.trim()).length()==0){
//         model.addAttribute("birthplace_status","请输入籍贯");
//         model.addAttribute("birthplace_value","");
//         error = true;
//     }else if(school.isEmpty()||(school=school.trim()).length()==0){
//         model.addAttribute("school_status","请输入目前所在高校");
//         model.addAttribute("school_value","");
//         error = true;
//     }else if(research_direct.isEmpty()||(research_direct=research_direct.trim()).length()==0){
//         model.addAttribute("research_direct_status","请输入研究方向");
//         model.addAttribute("research_direct_value","");
//         error = true;
//     }else if(picture.isEmpty()){
//         model.addAttribute("picture_status","请上传你的个人头像");
//         error = true;
//     }else if(self_introduction.isEmpty()||(self_introduction=self_introduction.trim()).length()==0){
//         model.addAttribute("self_introduction_status","请输入你的个人简介");
//         model.addAttribute("self_introduction_value","");
//         error = true;
//     }
//    String path="";
//     if(!(picture.isEmpty()) && (path=getFilePath(picture)).isEmpty())
//     {
//         model.addAttribute("picture_status","个人头像上传失败,请稍后重试,或联系管理员");
//         error = true;
//     }
//     if(error==true){
//         return new ModelAndView("registry","registry",model);
//     }
//     UserInfo userInfo = new UserInfo(username,email,name,sex,tel,birthplace,school,identity,research_direct,path,self_introduction);
//     userInfoServer.save(userInfo);
//     PasswordEncoder encoder = new BCryptPasswordEncoder();
//
//     User user = new User(username, encoder.encode(firstpassword),identity,true,true,true,true);
//     userServer.save(user);
//
//
//     return new ModelAndView("registry_success","registry",model);
//    }
//    public static boolean isChineseStr(String str) {
//        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
//        char c[] = str.toCharArray();
//        for (int i = 0; i < c.length; i++) {
//            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
//            if (!matcher.matches()) {
//                return false;
//            }
//        }
//        return true;
//    }
//    public static String getFilePath(MultipartFile picture){
//        try {
//               File path = new File(ResourceUtils.getURL("classpath:").getPath());//classes
//               int index = path.getAbsolutePath().lastIndexOf("\\");
//               if (!path.exists()) {
//                   path = new File("");
//               }//转到上一级目录
//               File upLoad = new File(path.getAbsolutePath().substring(0, index), "resources/static/userimg/");
//               if (!upLoad.exists()) {
//                   upLoad.mkdirs();
//               }
//               File desFile = new File(upLoad + "/" + picture.getOriginalFilename());
//               picture.transferTo(desFile);
//               return desFile.getAbsolutePath();
//           } catch (FileNotFoundException e) {
//              return "null";
//           } catch (IOException e) {
//               return "null";
//           }
//       }
//}
