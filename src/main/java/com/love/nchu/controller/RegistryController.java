package com.love.nchu.controller;

import com.love.nchu.demain.ErrorVo;
import com.love.nchu.demain.ReviewTable;
import com.love.nchu.demain.User;
import com.love.nchu.demain.UserInfo;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.*;
import com.love.nchu.tool.EmailTool;
import com.love.nchu.tool.LoginTool;
import com.love.nchu.tool.RegistryTool;
import com.love.nchu.tool.TitleTool;
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
    TitleEditServer titleEditServer;
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

    @PostMapping("/registry/email/code")
    public void getCode(@RequestBody String email) {
        date = new Date();
        System.out.println(email);
        code = EmailTool.getCode();
        System.out.println(code);
        mailServer.sendSimpleMail(email, "欢迎注册TDN之家账号", "本次的验证码为(十分钟内有效):" + code);
    }

    @GetMapping("/registry/step2")
    public ModelAndView step2() {

        return new ModelAndView("step2");
    }

    @GetMapping("/registry/step3")
    public ModelAndView step3() {

        return new ModelAndView("step3");
    }

    @GetMapping("/registry/fillbasicinformation")
    public ModelAndView fill(Model model) {
        model.addAttribute("TitleEdit",TitleTool.getTitle(titleEditServer));
        return new ModelAndView("fillbasicinformation", "model", model);
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/registry/fillbasicinformationvalidator")
    public String save(Model model, UserInfo userInfo, @RequestParam(name = "file", required = false)
            MultipartFile file,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

        try {
            if (!LoginTool.loginValidator(userServer, userInfo.getUsername(), userInfo.getPassword())) {
                return "用户名或密码错误";
            }
        } catch (Exception e) {
            return "服务器错误,操作失败";
        }

        if (RegistryTool.isFillBasicInformation(userInfoServer, userInfo.getUsername())) {
            return "个人信息已经填写，如果需要修改。请登录后到个人中心修改";
        }
        RegistryTool.Save_Image(file, userInfo, img_path, img_vm_path);
        RegistryTool.saveUserInfo(userInfoServer, userServer, userInfo);

        if (!userInfo.getUsername().equals("admin"))
            reviewTableServer.save(new ReviewTable(userInfo.getUsername(), new Date(), "未处理", "未处理", "warning"));
        // ErrorVo errorVo = new ErrorVo("");
        System.out.println(userInfo);
        System.out.println(file.getOriginalFilename());

        return "success";
    }

    @GetMapping("/registry/index")
    public ModelAndView index() {
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/registry/login")
    public ModelAndView login() {
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/registry/step1")
    public Object validator(@RequestBody userTem usertem) throws Exception {

        System.out.println(usertem);
        ErrorVo errorVo = new ErrorVo("");
        if (RegistryTool.isUsernameExist(userServer, usertem.getUsername())) {
            errorVo.setData("用户名已存在");//用户名已存在
            return errorVo;
        }
        if (RegistryTool.isEmailExist(userServer, usertem.getEmail())) {
            errorVo.setData("邮箱已被注册");//邮箱已被注册
            return errorVo;
        }

        if ((!RegistryTool.codeVolidator(code, usertem.getVcode())) || date==null) {
            errorVo.setData("验证码错误");//验证码错误
            return errorVo;
        }
        if (!EmailTool.isNotExpiredCheck(date, new Date())) {
            errorVo.setData("验证码已过期，请重新获取");//验证码过期
            return errorVo;
        }
        User user;
        if (usertem.getUsername().equals("admin")) {
            user = new User(usertem.getUsername(), SHAencrypt.encryptSHA(usertem.getFirstpassword()), "admin"
                    , usertem.getEmail(), true, true, true, true, false);
        } else {
            user = new User(usertem.getUsername(), SHAencrypt.encryptSHA(usertem.getFirstpassword()), "ordinary"
                    , usertem.getEmail(), true, true, true, false, false);
        }
        userServer.save(user);
        return errorVo;
    }

    @RequestMapping(value = "/registry", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registry(Model model) {
        model.addAttribute("TitleEdit", TitleTool.getTitle(titleEditServer));
        return new ModelAndView("registry", "model", model);
    }
}