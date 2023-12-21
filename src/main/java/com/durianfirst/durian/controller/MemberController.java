package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.MemberJoinDTO;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.service.MemberService;
import com.durianfirst.durian.validator.CheckValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
/*@RequestMapping("/member")*/
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final CheckValidator checkValidator;

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


    //유효성 검사
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkValidator);
    }

    @GetMapping("/member/register")
    public void register(){
        log.info("==================register get=====================");
    }

    @PostMapping("/member/register")
    public String registerPost(MemberJoinDTO memberJoinDTO, Errors errors, RedirectAttributes redirectAttributes,Model model){

        log.info("========================register post============================");
        log.info(memberJoinDTO);

        try{
            memberService.register(memberJoinDTO);

            if (errors.hasErrors()) {
                /* 회원가입 실패 시 입력 데이터 유지 */
                model.addAttribute("dto", memberJoinDTO);

                /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
                Map<String, String> validatorResult = memberService.validateHandling(errors);
                for (String key : validatorResult.keySet()) {
                    model.addAttribute(key, validatorResult.get(key));
                }

                /* 회원가입 페이지로 리턴 */
                return "/member/register";
            }

        }catch(MemberService.MidExistException e){
            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/register"; //MidExistException 발생 시 /member/register로 redirect
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/member/login"; //회원가입 후 로그인
    }

    @GetMapping("/member/mypage")
    public String mypagedRead(Principal principal, Model model) {

        if(principal == null){
            //로그인되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/member/login";
        }

        String mid = principal.getName();                   //mid에 로그인 정보를 받음
        Member member = memberRepository.findByMid(mid);    //findbymid로 유저 정보 찾아서 member에 저장

        log.info("유저 아이디 : " + principal.getName());

        model.addAttribute("member", member);    //model로 member에 담긴 정보를 인덱스 프론트에 넘김
        return "member/mypage";
    }


    @GetMapping("/member/modify")
    public String modify(Principal principal, Model model) {

        if(principal == null){
            //로그인되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/member/login";
        }

        String mid = principal.getName();
        Member member = memberRepository.findByMid(mid);

        model.addAttribute("member", member);

        return "/member/modify";
    }

    @PostMapping("/member/modify")
    public String updateMember(@Valid MemberJoinDTO joinDTO, Model model) {

        model.addAttribute("member", joinDTO);
        memberService.updateMember(joinDTO);
        return "redirect:/member/mypage";
    }

    @GetMapping("/member/checkPassword")
    public String memberWithdrawalForm() {
        return "/member/checkPassword";
    }
    @PostMapping("/member/checkPassword")
    public String memberWithdrawal(@RequestParam String password, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boolean result = memberService.deleteMember(userDetails.getUsername(), password);

        if (result) {
            return "redirect:/";
        } else {
            model.addAttribute("mpw", "비밀번호가 맞지 않습니다.");
            return "/member/checkPassword";
        }
    }





}
