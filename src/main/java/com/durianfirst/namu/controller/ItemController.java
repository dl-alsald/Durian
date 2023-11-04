package com.durianfirst.namu.controller;

import com.durianfirst.namu.dto.ItemDTO;
import com.durianfirst.namu.dto.ItemImgDTO;
import com.durianfirst.namu.dto.PageRequestDTO;
import com.durianfirst.namu.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/item")
@Log4j2
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/")
    public String index(){
        return "redirect:/item/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list.....");

        model.addAttribute("result", itemService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register get....");
    }

    @PostMapping("/register")
    public String registerPost(ItemDTO itemDTO, RedirectAttributes redirectAttributes){

        log.info("dto..." +itemDTO);

        Long ino = itemService.register(itemDTO);

        redirectAttributes.addFlashAttribute("msg", ino);

        return "redirect:/item/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long ino, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("ino: " + ino);

        ItemDTO itemDTO = itemService.read(ino);

        model.addAttribute("dto", itemDTO);
    }

    @PostMapping("/remove")
    public String remove(long ino, RedirectAttributes redirectAttributes) {

        log.info("ino: " + ino);

        itemService.remove(ino);

        redirectAttributes.addFlashAttribute("msg", ino);

        return "redirect:/item/list";
    }

    @PostMapping("/modify")
    public String modify(ItemDTO itemDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify..........");
        log.info("dto: " + itemDTO);

        itemService.modify(itemDTO);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("ino", itemDTO.getIno());

        return "redirect:/item/read";
    }


}
