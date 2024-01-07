package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.ShippingDTO;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.repository.ShippingRepository;
import com.durianfirst.durian.service.MemberService;
import com.durianfirst.durian.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final ShippingService shippingService;

    private final ShippingRepository shippingRepository;


    @GetMapping("/mypage/main")
    public String mainpage(){
        log.info("====main====");

        return "mypage/main";
    }



    /*@GetMapping("/mypage/shipping")
    public String shippinglist(Model model){
        log.info("====shipping====");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String mid = userDetails.getUsername();

        model.addAttribute("shippingDTO", shippingService.readAll(mid));

        log.info("shippingService.readAll(mid)");

        return "mypage/shipping";
    }*/





    @GetMapping("mypage/shipping/register")
    public String shippingRegister(Model model) {

        model.addAttribute("shippingDTO", new ShippingDTO());
        log.info("register");
        return "mypage/shipping";
    }


    @PostMapping(value = "mypage/shipping/register")
    public String shippingRegister(@Valid ShippingDTO shippingDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

        log.info("배송지 등록");

        String loggedId = principal.getName();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "";
        }

        shippingDto.setMid(loggedId);

        // 기존의 기본 배송지 해제
        shippingService.updateDefaultShipping(loggedId);

        Long sno = shippingService.register(shippingDto);

        redirectAttributes.addFlashAttribute("result", sno);

        log.info(sno);

        return "redirect:/mypage/shipping/list";
    }


    @GetMapping("/shipping/read{sno}")
    public String read(long sno, Model model){

        ShippingDTO shippingDto = shippingService.readOne(sno);

        model.addAttribute("shippingDto", shippingDto);


        return "shipping/shippingRead";
    }

    @GetMapping("/shipping/modify")
    public String Modifyread(long sno, Model model){

        ShippingDTO shippingDto = shippingService.readOne(sno);

        model.addAttribute("shippingDto", shippingDto);


        return "shipping/shippingModify";
    }

    @PostMapping("/shipping/modify")
    public String shippingModify(ShippingDTO shippingDTO, RedirectAttributes redirectAttributes) {
        log.info("shipping modify");
        log.info("shippingDTO: " + shippingDTO);

        shippingService.modify(shippingDTO);

        // 'sno' 값을 RedirectAttributes에 추가하여 URL에 해당 값을 전달
        redirectAttributes.addAttribute("sno", shippingDTO.getSno());

        // Redirect 시, URL에 'sno' 값을 포함하여 리다이렉트합니다.
        return "shipping/read";
    }

    @PostMapping("/shipping/remove")
    public String remove(@RequestParam("sno") long sno, RedirectAttributes redirectAttributes){

        log.info("sno" + sno);

        shippingService.remove(sno);

        redirectAttributes.addFlashAttribute("msg", sno);

        return "shipping/shipping";
    }


}
