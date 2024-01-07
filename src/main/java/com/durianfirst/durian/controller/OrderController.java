package com.durianfirst.durian.controller;


import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.service.ItemService;
import com.durianfirst.durian.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final OrderService orderService;

    private final ItemService itemService;

    private final MemberRepository memberRepository;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("pageRequestDTO: " + pageRequestDTO);


        model.addAttribute("result", orderService.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(long ino, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model, OrderDTO orderDTO, MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {
        log.info("orderDTO : "+orderDTO);

        Long ono = orderService.register(orderDTO);

        ItemDTO itemDTO = itemService.getItem(ino);

        redirectAttributes.addFlashAttribute("msg",ono);
        model.addAttribute("idto", itemDTO);
        model.addAttribute("odto", orderDTO);
        model.addAttribute("mdto", memberJoinDTO);
        return "redirect:/orderRead";

    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/read", "/modify"})
    public void read(long ono, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("ono : "+ono);

        OrderDTO orderDTO = orderService.getOrder(ono);

        model.addAttribute("dto", orderDTO);
    }


}
