package com.example.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 需要拦截的路径
        String[] addPathPatterns = {
                "/**"
        };
        // 不需要拦截的路径
        String[] excludePathPatterns = {
                "/",
                "/js/**",
                "/layui/**",
                "/image/**",
                "/login_in",
                "/css/**",
                "/createImg"
        };
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }
}
