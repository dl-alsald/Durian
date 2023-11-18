package com.durianfirst.durian.controller;


import com.durianfirst.durian.dto.ItemDTO;
import com.durianfirst.durian.dto.PageRequestDTO;
import com.durianfirst.durian.service.ItemService;
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


    @GetMapping("/")
    public String mainindex(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list...............");
        log.info("pageRequestDTO: " + pageRequestDTO);


        return "redirect:/index";
    }
    @GetMapping("/index")
    public void index(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO: " + pageRequestDTO);


        model.addAttribute("item", itemService.getList(pageRequestDTO));

    }

    @GetMapping("/about")
    public void about() {

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
    public void services() {

    }
}
