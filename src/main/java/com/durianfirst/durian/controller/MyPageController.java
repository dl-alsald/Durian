package com.durianfirst.durian.controller;

import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyPageController {

    private final MemberRepository memberRepository;

    @GetMapping("/mypage")
    public String myPagedRead(Principal principal, Model model) {

        if(principal == null){
            //로그인되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/member/login";
        }

        String mid = principal.getName();                   //mid에 로그인 정보를 받음
        Member member = memberRepository.findByMid(mid);    //findbymid로 유저 정보 찾아서 member에 저장

        log.info("유저 아이디 : " + principal.getName());

        model.addAttribute("member", member);    //model로 member에 담긴 정보를 인덱스 프론트에 넘김
        return "/mypage/mypage";
    }

}
