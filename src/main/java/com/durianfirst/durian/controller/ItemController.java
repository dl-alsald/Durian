package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.ItemDTO;
import com.durianfirst.durian.dto.PageRequestDTO;
import com.durianfirst.durian.entity.Items;
import com.durianfirst.durian.service.DaangnService;
import com.durianfirst.durian.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class ItemController {

    private final DaangnService daangnService;

    private final ItemService itemService;

    @GetMapping("/daangnfind")
    public String df(Model model) throws Exception{

        List<Items> itemList = daangnService.getItemsDatas();
        model.addAttribute("news", itemList);

        return "daangnfind";
    }

    @PostMapping("/daangnfind")
    public String register(ItemDTO itemDTO, RedirectAttributes redirectAttributes) {
        log.info("itemDTO : "+itemDTO);

        Long ino = itemService.register(itemDTO);

        redirectAttributes.addFlashAttribute("msg",ino);

        return "redirect:/daangnfind";

    }

    @GetMapping("/item")
    public String index(){
        return "redirect:/item/list";
    }

    @GetMapping("/item/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list.....");

        model.addAttribute("result", itemService.getList(pageRequestDTO));
    }

    @GetMapping("/item/register")
    public void register(){
        log.info("register get....");
    }

    @PostMapping("/item/register")
    public String registerPost(ItemDTO itemDTO, RedirectAttributes redirectAttributes){

        log.info("dto..." +itemDTO);

        Long ino = itemService.register(itemDTO);

        redirectAttributes.addFlashAttribute("msg", ino);

        return "redirect:/item/list";
    }

    @GetMapping({"/item/read", "/item/modify"})
    public void read(long ino, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("ino: " + ino);

        ItemDTO itemDTO = itemService.read(ino);

        model.addAttribute("dto", itemDTO);
    }

    @PostMapping("/item/remove")
    public String remove(long ino, RedirectAttributes redirectAttributes) {

        log.info("ino: " + ino);

        itemService.remove(ino);

        redirectAttributes.addFlashAttribute("msg", ino);

        return "redirect:/item/list";
    }

    @PostMapping("/item/modify")
    public String modify(ItemDTO itemDTO, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify..........");
        log.info("dto: " + itemDTO);

        itemService.modify(itemDTO);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("ino", itemDTO.getIno());

        return "redirect:/item/read";
    }

//    @PostMapping("/daangnfind")
//    public String registerMultipleItems(@ModelAttribute("news") List<ItemDTO> news, RedirectAttributes redirectAttributes) {
//        log.info("itemDTOList: " + news);
//
//        // 여러 개의 아이템을 반복적으로 저장
//        for (ItemDTO itemDTO : news) {
//            Long ino = itemService.register(itemDTO);
//            // 여기에서 다른 처리 로직을 수행하거나 필요한 경우 추가 작업을 수행할 수 있습니다.
//        }
//
//        redirectAttributes.addFlashAttribute("msg", "Items registered successfully");
//
//        return "redirect:/daangnfind";
//    }
}
