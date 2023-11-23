package com.durianfirst.namu.config;


import com.durianfirst.namu.security.CustomSocialLoginSuccessHandler;
import com.durianfirst.namu.security.CustomUserDetailsService;
import com.durianfirst.namu.security.handler.Custom403Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("SecurityConfig.filterChain() 로그인 시 실행");

        // 커스텀 로그인 페이지
        http.formLogin()
                .loginPage("/member/login")                                                // Form 로그인 기능 작동, 커스텀 로그인 페이지
                .defaultSuccessUrl("/admin/index")                                               // 로그인 성공시 index 페이지로 이동
                .failureUrl("/member/login/error")                      // 로그인 실패 시 이동할 URL 설정
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))   // 로그아웃 URL
                .logoutSuccessUrl("/admin/index");                                               // 로그아웃 성공시 이동할 URL

        http.csrf().disable();


        http.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30);

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        http.oauth2Login()
                .loginPage("/member/login")
                .successHandler(authenticationSuccessHandler());;


        return http.build();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("==========web configure=============");

        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new Custom403Handler();
    }

    // 시큐리티 설정에 소셜 로그인 성공 시 페이지 이동 핸들러 추가
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {

        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

}
