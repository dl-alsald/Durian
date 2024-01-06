package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.ItemDTO;
import com.durianfirst.durian.entity.Items;
import com.durianfirst.durian.entity.News;
import com.durianfirst.durian.service.DaangnService;
import com.durianfirst.durian.service.DurianService;
import com.durianfirst.durian.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
public class DurianController {

    private final DurianService durianService;




    public DurianController(DurianService durianService) {this.durianService = durianService;
    }

    @GetMapping("/hotelfind")
    public String hf(Model model) throws Exception{

        List<News> newsList = durianService.getNewsDatas();
        model.addAttribute("news", newsList);

        return "hotelfind";
    }


    @GetMapping("/admin/index")
    public void index(){}



}
