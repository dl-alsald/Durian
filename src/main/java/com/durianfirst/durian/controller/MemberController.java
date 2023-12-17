package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.MemberJoinDTO;
import com.durianfirst.durian.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
/*@RequestMapping("/member")*/
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String login(@RequestParam(value = "error", required = false)String error, @RequestParam(value = "exception", required = false)String exception, String logout, Model model){
        log.info("================login get================");
        log.info("logout : " + logout);

        if(logout != null){
            log.info("====================user logout=====================");
        }

        /* 에러 */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "member/login";
    }

    @GetMapping("/member/register")
    public void register(){
        log.info("==================register get=====================");
    }

    @PostMapping("/member/register")
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

}
