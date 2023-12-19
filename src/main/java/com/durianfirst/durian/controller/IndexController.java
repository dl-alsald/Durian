package com.durianfirst.durian.controller;


import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Items;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class IndexController {


    private final ItemService itemService;

    private final QuestionService questionService;

    private final AnswerService answerService;

    private final DaangnService daangnService;

    private final EventService eventService;

    private final MemberRepository memberRepository;

    private final NoticeService noticeService;



    @GetMapping("/")
    public String mainindex(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...............");
        log.info("pageRequestDTO: " + pageRequestDTO);


        return "redirect:/index";
    }
    @GetMapping("/index")
    public String index(PageRequestDTO pageRequestDTO, Model model, Principal principal) throws Exception{

        log.info("pageRequestDTO: " + pageRequestDTO);


        model.addAttribute("item", itemService.getList(pageRequestDTO));

        List<Items> itemList = daangnService.getItemsDatas();
        model.addAttribute("news", itemList);

        //principal로 로그인 정보 가져옴
        // model로 index로 넘겨줌
        if (principal != null) {

            String mid = principal.getName();                   //mid에 로그인 정보를 받음
            Member member = memberRepository.findByMid(mid);    //findbymid로 유저 정보 찾아서 member에 저장

            log.info("유저 아이디 : " + principal.getName());

            model.addAttribute("member",member);    //model로 member에 담긴 정보를 인덱스 프론트에 넘김

            return "/index";
        } else {
            // 로그인이 안 된 경우 로그인 없는 뷰로 이동
            return "/member/login";


        }
    }

    @GetMapping("/about")
    public void about() {

    }

    @GetMapping("/contact")
    public void contact() {

    }

    @PostMapping("/contact")
    public String contactpost(ItemDTO itemDTO, RedirectAttributes redirectAttributes) {

        log.info("dto..." +itemDTO);

        Long ino = itemService.register(itemDTO);

        redirectAttributes.addFlashAttribute("msg", ino);

        return "redirect:/index";

    }

    @GetMapping("/properties")
    public void properties(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list.....");

        model.addAttribute("result", itemService.getList(pageRequestDTO));

    }

    @GetMapping("/article")
    public void propertysingle(long ino, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("ino : "+ino);

        ItemDTO itemDTO = itemService.getItem(ino);

        model.addAttribute("dto", itemDTO);

    }

    @GetMapping("/services")
    public void services(PageRequestedDTO pageRequestDTO, Model model) {
        PageResponsedDTO<QuestionDTO> responseDTO = questionService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping("/event")
    public void event(PageRequestDTO pageRequestDTO, Model model){

        log.info("pageRequestDTO: " + pageRequestDTO);


        model.addAttribute("eresult", eventService.getList(pageRequestDTO));

    }

    @GetMapping({"/eventread"})
    public void eread(long eno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("eno : "+eno);

        EventDTO eventDTO = eventService.getEvent(eno);

        model.addAttribute("edto", eventDTO);
    }

    @GetMapping("/notice")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list.............."+pageRequestDTO);

        model.addAttribute("nresult", noticeService.getList(pageRequestDTO));

    }

    @GetMapping({"/noticeread"})
    public void nread(long nno, @ModelAttribute("requestDTO") PageRequestDTO
            requestDTO, Model model) {
        log.info("nno: "+nno );

        NoticeDTO dto = noticeService.read(nno);

        model.addAttribute("ndto", dto);
    }
}
