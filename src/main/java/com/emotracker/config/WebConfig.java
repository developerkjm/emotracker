package com.emotracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // http://localhost:8080/files/파일명 으로 접근 가능 <= 브라우저 접근경로
        registry.addResourceHandler("/files/**")
                // 실제 저장경로.
                .addResourceLocations("file:///C:/uploads/");

    }
}
