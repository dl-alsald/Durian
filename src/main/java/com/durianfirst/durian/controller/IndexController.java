package com.durianfirst.durian.controller;


import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Items;
import com.durianfirst.durian.service.AnswerService;
import com.durianfirst.durian.service.DaangnService;
import com.durianfirst.durian.service.ItemService;
import com.durianfirst.durian.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public void properties() {

    }

    @GetMapping("/article")
    public void propertysingle(long ino, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("ino : "+ino);

        ItemDTO itemDTO = itemService.getItem(ino);

        model.addAttribute("dto", itemDTO);

    }

    /* services -> question/qna 변경 => questionController */
}
