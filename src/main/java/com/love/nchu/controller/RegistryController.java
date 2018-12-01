package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.GlobalVariable;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.MailServer;
import com.love.nchu.service.ReviewTableServer;
import com.love.nchu.service.UserInfoServer;
import com.love.nchu.service.UserServer;
import com.love.nchu.tool.EmailTool;
import com.love.nchu.tool.LoginTool;
import com.love.nchu.tool.RegistryTool;
import com.love.nchu.vo.userTem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class RegistryController {
    @Autowired
    UserInfoServer userInfoServer;
    @Autowired
    UserServer userServer;
    @Autowired
    ReviewTableServer reviewTableServer;

    @Autowired
    MailServer mailServer;

    @Value("${spring.img.vm.path}")
    String img_vm_path;

    @Value("${spring.img.ab.path}")
    String img_ab_path;

    @Value("${spring.img.path}")
    String img_path;

    String code;
    Date date;
    @PostMapping("registry/email/code")
    public void getCode(@RequestBody  String email){
        date = new Date();
        System.out.println(email);
        code = EmailTool.getCode();
        System.out.println(code);
        mailServer.sendSimpleMail(email,"欢迎注册TDN之家账号","本次的验证码为(十分钟内有效):"+code);
    }

    @GetMapping("/registry/step2")
    public ModelAndView step2(){

        return new ModelAndView("step2");
    }
    @GetMapping("/registry/step3")
    public ModelAndView step3(){

        return new ModelAndView("step3");
    }

    @GetMapping("/registry/fillbasicinformation")
    public ModelAndView fill(Model model){
        model.addAttribute("TitleEdit", GlobalVariable.titleEdit);
        return new ModelAndView("fillbasicinformation","model",model);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/registry/fillbasicinformationvalidator")
    public String save(Model model, UserInfo userInfo, @RequestParam(name="file",required = false)
            MultipartFile file,
             @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {

        try{
        if(!LoginTool.loginValidator(userServer,userInfo.getUsername(),userInfo.getPassword())){
            return "用户名或密码错误";
        }
        }catch (Exception e){
            return "服务器错误,操作失败";
        }

        if(RegistryTool.isFillBasicInformation(userInfoServer,userInfo.getUsername())){
            return "个人信息已经填写，如果需要修改。请登录后到个人中心修改";
        }

        RegistryTool.Save_Image(file,userInfo,img_path,img_vm_path);
        RegistryTool.saveUserInfo(userInfoServer,userServer,userInfo);
       // ErrorVo errorVo = new ErrorVo("");
        System.out.println(userInfo);
        System.out.println(file.getOriginalFilename());

        return  "success";
    }
    @GetMapping("/registry/index")
    public ModelAndView index(){
        return new ModelAndView("redirect:/index");
    }


    @PostMapping("/registry/step1")
    public Object validator(@RequestBody userTem usertem) throws  Exception{

        System.out.println(usertem);
        ErrorVo errorVo = new ErrorVo("");

        if(RegistryTool.isUsernameExist(userServer,usertem.getUsername())){
            errorVo.setData("用户名已存在");//用户名已存在
            return errorVo;
        }
        if(RegistryTool.isEmailExist(userServer,usertem.getEmail())){
            errorVo.setData("邮箱已被注册");//邮箱已被注册
            return errorVo;
        }

        if(!RegistryTool.codeVolidator(code,usertem.getVcode())){
            errorVo.setData("验证码错误");//验证码错误
            return errorVo;
        }

      User  user  = new User(usertem.getUsername(), SHAencrypt.encryptSHA(usertem.getFirstpassword()),"ordinary"
                ,usertem.getEmail(),true,true,true,false,false);
        userServer.save(user);
        return errorVo;
    }
    @RequestMapping(value="/registry",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView registry(Model model)
    {
        model.addAttribute("TitleEdit", GlobalVariable.titleEdit);
        return new ModelAndView("registry","model",model);
    }
//    @InitBinder
//    public void initBinder(ServletRequestDataBinder binder){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
//    @RequestMapping(value="/registry_action",method = {RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView registry_action(
//            @Valid UserInfo userInfo,
//            BindingResult errors, Model model,
//            @RequestParam(name="file",required = false) MultipartFile file
//            // @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
//    )throws Exception{
//        field_init(model,userInfo);
//        model.addAttribute("TitleEdit",GlobalVariable.titleEdit);
//       if(!iserror(errors,model,file,userInfo)){//校验输入是否正确
//           if(Save_Image(file,userInfo)){//存储图像到目录中，把路径存到数据库中
//               userInfo.setAge(get_Age(userInfo.getBirthDate()));
//               if(userInfo.getSex().equals("man")){
//                   userInfo.setSex("男");
//               }
//               else userInfo.setSex("女");
//               userInfo.setRegistry_date(MyDate.getDate());
//               userInfoServer.save(userInfo);//存储用户详细信息
//               if(userInfo.getUsername().equals("admin")){
//
//                   userServer.save(new User( userInfo.getUsername(), SHAencrypt.encryptSHA(userInfo.getFirstpassword()),userInfo.getIdentity(),false,true,false,true,true));
//               }
//               else {
//                   userServer.save(new User(userInfo.getUsername(), SHAencrypt.encryptSHA(userInfo.getFirstpassword()), userInfo.getIdentity(), false, true, false, false,false));
//                   reviewTableServer.save(new ReviewTable(userInfo.getUsername(), new Date(), "未处理", "未处理", "warning"));
//               }
//               return new ModelAndView("registry_success","mode1",model);
//           }
//       }
//        return new ModelAndView("fillbasicinformation","registry",model);
//    }
//       public  void  field_init(Model model,UserInfo userInfo){
//           model.addAttribute("username_value",userInfo.getUsername());
//           model.addAttribute("username_status","");
//           model.addAttribute("firstpassword_value",userInfo.getFirstpassword());
//           model.addAttribute("secondpassword_value",userInfo.getSecondpassword());
//           if(userInfo.getBirthDate()!=null){
//               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                String dateString = formatter.format(userInfo.getBirthDate());
//               model.addAttribute("birthDate_value",dateString);
//           }else   model.addAttribute("birthDate_value","");
//           model.addAttribute("birthDate_status","");
//           model.addAttribute("email_value",userInfo.getEmail());
//           model.addAttribute("email_status","");
//           model.addAttribute("name_value",userInfo.getName());
//           model.addAttribute("name_status","");
//           model.addAttribute("sex_value",userInfo.getSex());
//           model.addAttribute("sex_status","");
//           model.addAttribute("tel_value",userInfo.getTel());
//           model.addAttribute("tel_status","");
//           model.addAttribute("birthplace_value",userInfo.getBirthplace());
//           model.addAttribute("birthplace_status","");
//           model.addAttribute("school_value",userInfo.getSchool());
//           model.addAttribute("school_status","");
//           model.addAttribute("identity_value",userInfo.getIdentity());
//           model.addAttribute("identity_status","");
//           model.addAttribute("teachername_value",userInfo.getTeachername());
//           model.addAttribute("teachername_status","");
//           model.addAttribute("research_direct_value",userInfo.getResearch_direct());
//           model.addAttribute("research_direct_status","");
//           model.addAttribute("file_status","");
//           model.addAttribute("self_introduction_value",userInfo.getSelf_introduction());
//           model.addAttribute("self_introduction__status","");
//       }
//       public  boolean iserror(BindingResult errors,Model model,MultipartFile file,UserInfo userInfo){
//           boolean iserror = false;
//           if(errors.hasErrors()){
//               iserror =true;
//               errors.getAllErrors().stream().forEach(error->{
//                   FieldError fieldError = (FieldError)error;
//                   if(fieldError.getField().equals("teachername")&& userInfo.getIdentity().equals("teacher")) {
//                       model.addAttribute(fieldError.getField()+"_status","");
//                   }
//                   else{
//                       model.addAttribute(fieldError.getField()+"_status",fieldError.getDefaultMessage());
//                   }
//               });
//           }
//          else if(file.isEmpty()){
//               model.addAttribute("file_status","请选择你的个人头像");
//               iserror = true;
//           }else if(!((file.getOriginalFilename().substring(file.getOriginalFilename().length()-4).equals(".jpg")) || (file.getOriginalFilename().substring(file.getOriginalFilename().length()-4).equals(".png")) )){
//               model.addAttribute("file_status","只能上传.jpg/.png格式头像");
//               iserror = true;
//           }
//          else if(userInfoServer.isUserExistByUsername(userInfo.getUsername())!=null){
//               model.addAttribute("username_status","该用户名已被注册了");
//                iserror = true;
//           }else if(userInfoServer.isUserExistByEmail(userInfo.getEmail())!=null){
//               model.addAttribute("email_status","该邮箱被注册了");
//                iserror = true;
//           }else if(userInfoServer.isUserExistByTel(userInfo.getTel())!=null){
//               model.addAttribute("tel_status","该号码已被注册了");
//                iserror = true;
//           }else if(userInfo.getTeachername().equals("null")&&(userInfo.getIdentity().equals("student"))){
//               model.addAttribute("teachername_status","请输入指导老师名字");
//               iserror = true;
//           }  else if(!model.containsAttribute("firstpassword_status") && !model.containsAttribute("secondpassword_status")){
//               if(!userInfo.getFirstpassword().equals(userInfo.getSecondpassword())){
//                   model.addAttribute("firstpassword_status","两次密码不一致");
//                   model.addAttribute("secondpassword_status","两次密码不一致");
//                   iserror = true;
//               }
//           }
//           return iserror;
//       }
//       public boolean Save_Image(MultipartFile file,UserInfo userInfo){
//         File newfile = new File(img_path);
//         if(!newfile.exists()){
//             newfile.mkdirs();
//         }
//         File bfile = new File(newfile,userInfo.getUsername()+".jpg");
//         if(!bfile.exists()){
//             try{
//             bfile.createNewFile();
//             }catch(IOException e){
//                 e.printStackTrace();
//             }
//         }
//           try {
//               BufferedOutputStream out = new BufferedOutputStream(
//                       new FileOutputStream(bfile));//保存图片到目录下
//               out.write(file.getBytes());
//               out.flush();
//               out.close();
//               String filename = img_vm_path + userInfo.getUsername() + ".jpg";
//              userInfo.setPicture(filename);
//           } catch (FileNotFoundException e) {
//               e.printStackTrace();
//              return false;
//           } catch (IOException e) {
//               e.printStackTrace();
//               return false;
//           }
//        return true;
//       }
//    public int get_Age(Date birthDate){
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String s = sdf.format(birthDate);
//        int birthYear =Integer.parseInt(s.substring(0,4));
//        int nowYear = Integer.parseInt(MyDate.getDate().substring(0,4));
//        return nowYear-birthYear;
//    }
}
