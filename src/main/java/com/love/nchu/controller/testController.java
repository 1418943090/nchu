package com.love.nchu.controller;


import com.love.nchu.demain.Sign_in_Time;
import com.love.nchu.service.MailServer;
import com.love.nchu.service.Sign_in_StatusServer;
import com.love.nchu.service.Sign_in_TimeServer;
import com.love.nchu.service.UserInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

//    @Value("${spring.img.ab.path}")
//    String staticLocations;

  @Autowired
    Sign_in_TimeServer sign_in_timeServer;
  @Autowired
    Sign_in_StatusServer sign_in_statusServer;
@Autowired
    UserInfoServer userInfoServer;


  @Value("${spring.net.subnet_mask}")
   String subnetmask;
  @Value("${spring.net.gateway}")
  String gateway;


  @Autowired
    MailServer mailServer;

  @GetMapping("/mail")
  public void mailTest(){

      mailServer.sendSimpleMail("1525931170@qq.com","测试","狗子");



  }

//
//    public static String getIP(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        System.out.println("x-forwarded-for ip: " + ip);
//        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
//            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
//            if( ip.indexOf(",")!=-1 ){
//                ip = ip.split(",")[0];
//            }
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//            System.out.println("Proxy-Client-IP ip: " + ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//            System.out.println("WL-Proxy-Client-IP ip: " + ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_CLIENT_IP");
//            System.out.println("HTTP_CLIENT_IP ip: " + ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("X-Real-IP");
//            System.out.println("X-Real-IP ip: " + ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//            System.out.println("getRemoteAddr ip: " + ip);
//        }
//        System.out.println("获取客户端ip: " + ip);
//        return ip;
//    }
//
//  @RequestMapping("/test2")
//  public void test2(HttpServletRequest request){
//
////      System.out.println(subnetmask);
////      System.out.println(gateway);
////      System.out.println(getIP(request));
//
//    //  List<PerVo> list = permissionServer.getPerVo();
//
//      System.out.println(list);
//
//      for(Object p : list){
//
//          PerVo a = (PerVo) p;
//
//          System.out.println(a.toString());
//      }
//
//  }




    @RequestMapping("/test")
    public void test() throws Exception{

        Sign_in_Time s = new Sign_in_Time("(7:30~8:10)","(11:35~11:50)","(13:30~14:10)","(17:35~17:50)",
                "(18:30~19:10)","(20:55~23:00)","5-1","9-30","summer");


        Sign_in_Time s2 = new Sign_in_Time("(7:30~8:10)","(11:35~11:50)","(13:00~13:40)","(17:05~17:20)",
                "(18:30~19:10)","(20:55~23:00)","10-1","4-30","spring");

        sign_in_timeServer.save(s);
        sign_in_timeServer.save(s2);

//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
//        String s = format.format(date);
//
//        System.out.println(s);
//
//        List<String> list = userInfoServer.getAllStudentUsername();
//
//        System.out.println(list);
//
//
//
//        for(String name : list){
//            Sign_in_Status sign_in_status = new Sign_in_Status(name,s);
//            sign_in_statusServer.save(sign_in_status);
//        }
//
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        int month = cal.get(Calendar.MONTH);
//        int day = cal.get(Calendar.DAY_OF_MONTH);
//             Sign_in_Time sign_in_time=null;
//        month+=1;
////        if(month>=5 && month<10){
//             sign_in_time= sign_in_timeServer.getTimeBySeason("summary");
//        }else{
//                 sign_in_time = sign_in_timeServer.getTimeBySeason("spring");
//        }
//         System.out.println(sign_in_time.toString()) ;
//        System.out.println(staticLocations);
//          File file = new File(staticpath+File.separator+"img");
//          if(!file.exists()){
//              file.mkdirs();
//          }


//        System.out.println(staticLocations);
//
//        File newfile = new File(staticLocations+"img");
//        if(!newfile.exists())
//        {
//            System.out.println("AAAA");
//            newfile.mkdirs();
//        }
//
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//        System.out.println(path.getParent());
//        File f = new File(path.getParent()+File.separator+"")
//        System.out.print(path);
    }

}
