package com.durianfirst.namu.controller;


import com.durianfirst.namu.dto.MemberDto;
import com.durianfirst.namu.security.CustomUserDetailsService;
import com.durianfirst.namu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get.........");
        log.info("logout : "+ logout);

        if(logout != null){
            log.info("user logout......");
        }
    }



    @GetMapping("/join")
    public void joinGET(){
        log.info("join get....");
    }

    @PostMapping("/join")
    public String joinPost(MemberDto memberDto, RedirectAttributes redirectAttributes) {
        log.info("join post...");
        log.info(memberDto);

        try{
            memberService.join(memberDto);
        }catch (MemberService.MidExistException e){
            redirectAttributes.addFlashAttribute("error","mid");
            return "redirect:/member/join";
        }
        redirectAttributes.addFlashAttribute("result","success");
        return "redirect:/member/login";
    }

}
