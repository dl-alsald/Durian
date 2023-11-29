package com.durianfirst.namu.controller;

import com.durianfirst.namu.dto.MemberJoinDTO;
import com.durianfirst.namu.entity.Member;
import com.durianfirst.namu.repository.MemberRepository;
import com.durianfirst.namu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public void login(String errorCode, String logout){
        log.info("================login get================");
        log.info("logout : " + logout);

        if(logout != null){
            log.info("====================user logout=====================");
        }
    }

    @GetMapping("/register")
    public void register(){
        log.info("==================register get=====================");
    }

    @PostMapping("/register")
    public String registerPost(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes){

        log.info("========================register post============================");
        log.info(memberJoinDTO);

        try{
            memberService.register(memberJoinDTO);
        }catch(MemberService.MidExistException e){
            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/register"; //MidExistException 발생 시 /member/register로 redirect
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/member/login"; //회원가입 후 로그인
    }

    @GetMapping("/mypage")
    public void mypagedRead(Principal principal, Model model) {

        String mid = principal.getName();                   //mid에 로그인 정보를 받음
        Member member = memberRepository.findBymid(mid);    //findbymid로 유저 정보 찾아서 member에 저장

        log.info("유저 아이디 : " + principal.getName());

        model.addAttribute("member",member);    //model로 member에 담긴 정보를 인덱스 프론트에 넘김

    }
}
