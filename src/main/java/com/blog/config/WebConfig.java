package com.blog.config;

import com.blog.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/users/login/**")
                .excludePathPatterns("/users/register/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/articles/getByAid/**")
                .excludePathPatterns("/articles/getByUsername/**")
                .excludePathPatterns("/articles/getAll/**");
    }
}
