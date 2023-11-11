package com.durianfirst.namu.security.handler;

import com.durianfirst.namu.security.dto.MemberSecurityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomSocialLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("=====================================");
        log.info("CustomLoginSuccessHandler onAuthenticationSuccess......");
        log.info(authentication.getPrincipal());

        MemberSecurityDto memberSecurityDto = (MemberSecurityDto) authentication.getPrincipal();

        String encodedPw = memberSecurityDto.getMpw();

        //소셜 로그인이고 회원의 패스워드가 1111

        if(memberSecurityDto.isEnabled() &&
                (memberSecurityDto.getMpw().equals("1111") || passwordEncoder.matches("1111", memberSecurityDto.getMpw()))) {

            log.info("소설 로그인 회원 비밀번호 변경을 위한 페이지 이동");
            response.sendRedirect("/member/modify");

            return;
        } else {

            response.sendRedirect("/board/list");
        }
    }
}
