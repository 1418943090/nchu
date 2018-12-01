package com.love.nchu.tool;

import com.love.nchu.demain.User;
import com.love.nchu.security.SHAencrypt;
import com.love.nchu.service.UserServer;
import org.springframework.stereotype.Component;

@Component
public class PasswordTool {


    public static boolean vcoedCheck(String code1,String code2){

        if(code1==null || code2==null){
            return false;
        }
        if((code1).toLowerCase().equals((code2.toLowerCase()))){
            return true;
        }
        return false;
    }
    public static void updatePassword(UserServer userServer,String username,String newpassword) throws Exception{

        User user = userServer.findUserByUsername(username);
        user.setPassword(SHAencrypt.encryptSHA(newpassword));
        userServer.save(user);
    }
}
