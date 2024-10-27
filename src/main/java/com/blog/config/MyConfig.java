package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    private File file = new File("src\\main\\resources\\static\\images");

    //配置虚拟路径访问映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            String imageAddress = file.getCanonicalPath().toString();
            //映射图片保存地址
            registry.addResourceHandler("/images/**").addResourceLocations("IMAGES:" + imageAddress + "\\");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
