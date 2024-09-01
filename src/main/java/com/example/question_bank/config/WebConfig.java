//package com.example.question_bank.config;
//
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*") // 모든 출처 허용
//                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .exposedHeaders("Authorization").exposedHeaders("X-Forwarded-For").exposedHeaders("Content-Disposition")
//                .allowCredentials(true); // 크레덴셜 허용
//    }
//}