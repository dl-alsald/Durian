package com.durianfirst.durian.controller;

import com.durianfirst.durian.config.CustomUserDetails;
import com.durianfirst.durian.dto.MemberJoinDTO;
import com.durianfirst.durian.dto.ShippingDTO;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.repository.ShippingRepository;
import com.durianfirst.durian.service.MemberService;
import com.durianfirst.durian.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()") // 로그인한 사용자만 조회 가능 -> 비로그인 상태일 경우 로그인 화면으로 이동
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;



    @GetMapping("/login")
    public void login(String errorCode, String logout) {
        log.info("================login get================");
        log.info("logout : " + logout);

        if (logout != null) {
            log.info("====================user logout=====================");
        }
    }

    @GetMapping("/register")
    public void register() {
        log.info("==================register get=====================");
    }

    @PostMapping("/register")
    public String registerPost(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {

        log.info("========================register post============================");
        log.info(memberJoinDTO);

        try {
            memberService.register(memberJoinDTO);
        } catch (MemberService.MidExistException e) {
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

        model.addAttribute("member", member);    //model로 member에 담긴 정보를 인덱스 프론트에 넘김

    }

    @GetMapping("/modify")
    public String modify(Principal principal, Model model) {

        String mid = principal.getName();
        Member member = memberRepository.findBymid(mid);

        model.addAttribute("member", member);

        return "/member/modify";
    }

    @PostMapping("/modify")
    @ResponseBody
    public boolean update(@RequestBody MemberJoinDTO dto) {

        log.info("MemberRestController 진입");

        log.info("dto : " + dto);
        // 회원 정보 수정
        memberService.userInfoUpdate(dto);

        /** ========== 변경된 세션 등록 ========== **/
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) currentAuthentication.getPrincipal();

        // 사용자 정보 업데이트
        userDetails.setUsername(dto.getMid());
        userDetails.setPassword(dto.getMpw());

        // 변경된 Authentication으로 SecurityContextHolder 업데이트
        Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(userDetails, dto.getMpw(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(updatedAuthentication);

        return true;
    }

    }
