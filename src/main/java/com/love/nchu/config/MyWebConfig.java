package com.love.nchu.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//上传配置类
//图片放到/F:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
//public class WebAppConfig extends WebMvcConfigurationSupport {

    @Value("${spring.img.vm.path}") //  /images/people/
    String img_vm_path;

    @Value("${spring.img.ab.path}") // file:/E:/upload/images/people/
    String img_ab_path;

    @Value("${spring.paper.vm.path}")
    String paper_vm_path;

    @Value("${spring.paper.ab.path}")
    String paper_ab_path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(img_vm_path+"**").addResourceLocations(img_ab_path);
        registry.addResourceHandler(paper_vm_path+"**").addResourceLocations(paper_ab_path);

    }

}
