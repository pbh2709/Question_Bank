//package com.example.question_bank.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class SecurityConfig {
//
//
//
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        /**
//         http.formLogin(new Customizer<FormLoginConfigurer<HttpSecurity>>() {
//        @Override
//        public void customize(FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer) {
//        httpSecurityFormLoginConfigurer.loginPage("/login")
//        .defaultSuccessUrl()
//        }
//        });
//         */
//
//        http.formLogin(login -> login //
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/",true)
//                        .permitAll())
//                .logout(logout -> logout.logoutUrl("/logout"))
//                        .logout(logout -> logout.logoutSuccessUrl("/"))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/css/**", "/js/**", "/webjars/**", "images/**").permitAll()
//                        .requestMatchers("/","/register").permitAll()
//                        .requestMatchers("question_test","question_retest").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/question", "/question_list", "/question_details", "/question_edit","/tested_grading").hasRole("ADMIN")
//                        .requestMatchers("/questionimage", "/questionimage_list", "/questionimage_details", "/questionimage_edit").hasRole("ADMIN")
//                        .requestMatchers("/questionj", "/questionj_list", "/questionj_details", "/questionj_edit").hasRole("ADMIN")
//                        .requestMatchers("/questions", "/questions_list", "/questions_details", "/questions_view","/questions_edit").hasRole("ADMIN")
//                        .requestMatchers("/tested_list", "/tested_view", "/tested_result", "/testeds_list","/tested_list").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers("/create/**").hasAnyRole("ADMIN", "USER")
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        //허용할 url 설정
//        configuration.addAllowedOrigin("http://localhost:3000");
//        //허용할 헤더 설정
//        configuration.addAllowedHeader("*");
//        //허용할 http method
//        configuration.addAllowedMethod("*");
//        //사용자 자격 증명이 지원되는지 여부
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//
//    }
//
//}
//
//
