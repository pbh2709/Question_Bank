package com.example.question_bank.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Service
@Configuration
public class Config {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); // 기본 이름이 messages 리소스 번들을 로드
        messageSource.setDefaultEncoding("UTF-8"); // 인코딩 설정하지 않으면 한글은 깨져서 보임
        return messageSource;
    }
    @Configuration
    public class WebMvcConfiguration implements WebMvcConfigurer {

        private static final Locale DEFAULT_LOCALE = new Locale("en");

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(localeChangeInterceptor());
        }

        @Bean
        public LocaleChangeInterceptor localeChangeInterceptor() {
            LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
            localeChangeInterceptor.setParamName("language");
            return localeChangeInterceptor;
        }

        @Bean
        public LocaleResolver localeResolver() {
            CookieLocaleResolver localeResolver = new CookieLocaleResolver();
            localeResolver.setCookieName("language");
            localeResolver.setCookieMaxAge(3600); // 쿠키 유지 시간 (초)
            localeResolver.setDefaultLocale(Locale.KOREAN); // 기본 로케일
            return localeResolver;
        }
    }
}