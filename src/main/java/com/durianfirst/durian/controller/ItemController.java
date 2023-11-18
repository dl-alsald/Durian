package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.ItemDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
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
