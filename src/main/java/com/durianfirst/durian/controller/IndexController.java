package com.durianfirst.durian.controller;


import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Items;
import com.durianfirst.durian.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private final NoticeService noticeService;


    @GetMapping("/")
    public String mainindex(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...............");
        log.info("pageRequestDTO: " + pageRequestDTO);


        return "redirect:/index";
    }
    @GetMapping("/index")
    public void index(PageRequestDTO pageRequestDTO, Model model) throws Exception{

        log.info("pageRequestDTO: " + pageRequestDTO);


        model.addAttribute("item", itemService.getList(pageRequestDTO));

        List<Items> itemList = daangnService.getItemsDatas();
        model.addAttribute("news", itemList);



    }

    @GetMapping("/about")
    public void about() {

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

    @GetMapping("/contact")
    public void contact() {

    }

    @GetMapping("/properties")
    public void properties() {

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
}
