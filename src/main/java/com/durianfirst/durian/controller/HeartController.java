package com.durianfirst.durian.controller;

import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.security.LoginMember;
import com.durianfirst.durian.dto.ItemDTO;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.HeartQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.MemberRemoval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Objects;

@Controller
/*@RequestMapping("heart")*/
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class HeartController {

    private final HeartQueryRepository heartQueryRepository;

    private final MemberRepository memberRepository;


    @GetMapping("/list")
    public void list() {

    }

    @GetMapping("mypage/heart")
    public String viewListItem(Principal principal, @PageableDefault(size = 9, value = 9, sort =  "id", direction = Sort.Direction.DESC)
                               Pageable page, Model model){

        if(principal == null){
            return "redirect:/member/login";
        }

        String mid = principal.getName();
        Member member = memberRepository.findByMid(mid);

        log.info("유저 아이디 : " + principal.getName());

            Page<ItemDTO> heartItem = heartQueryRepository.findHeartItem(mid, page);
            model.addAttribute("itemList", heartItem.getContent());
            model.addAttribute("maxPage", 9);


        return "mypage/heart";
    }
}
