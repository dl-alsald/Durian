package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.ShippingDTO;
import com.durianfirst.durian.entity.ChatRoom;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.repository.ShippingRepository;
import com.durianfirst.durian.service.ChatService;
import com.durianfirst.durian.service.MemberService;
import com.durianfirst.durian.service.ShippingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final ShippingService shippingService;

    private final ShippingRepository shippingRepository;


    @GetMapping("/mypage/main")
    public String mainpage(){
        log.info("====main====");

        return "mypage/main";
    }



    /*@GetMapping("/mypage/shipping")
    public String shippinglist(Model model){
        log.info("====shipping====");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String mid = userDetails.getUsername();

        model.addAttribute("shippingDTO", shippingService.readAll(mid));

        log.info("shippingService.readAll(mid)");

        return "mypage/shipping";
    }*/





    @GetMapping("mypage/shipping/register")
    public String shippingRegister(Model model) {

        model.addAttribute("shippingDTO", new ShippingDTO());
        log.info("register");
        return "mypage/shipping";
    }


    @PostMapping(value = "mypage/shipping/register")
    public String shippingRegister(@Valid ShippingDTO shippingDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal) {

        log.info("배송지 등록");

        String loggedId = principal.getName();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "";
        }

        shippingDto.setMid(loggedId);

        // 기존의 기본 배송지 해제
        shippingService.updateDefaultShipping(loggedId);

        Long sno = shippingService.register(shippingDto);

        redirectAttributes.addFlashAttribute("result", sno);

        log.info(sno);

        return "redirect:/mypage/shipping/list";
    }


    @GetMapping("/shipping/read{sno}")
    public String read(long sno, Model model){

        ShippingDTO shippingDto = shippingService.readOne(sno);

        model.addAttribute("shippingDto", shippingDto);


        return "shipping/shippingRead";
    }

    @GetMapping("/shipping/modify")
    public String Modifyread(long sno, Model model){

        ShippingDTO shippingDto = shippingService.readOne(sno);

        model.addAttribute("shippingDto", shippingDto);


        return "shipping/shippingModify";
    }

    @PostMapping("/shipping/modify")
    public String shippingModify(ShippingDTO shippingDTO, RedirectAttributes redirectAttributes) {
        log.info("shipping modify");
        log.info("shippingDTO: " + shippingDTO);

        shippingService.modify(shippingDTO);

        // 'sno' 값을 RedirectAttributes에 추가하여 URL에 해당 값을 전달
        redirectAttributes.addAttribute("sno", shippingDTO.getSno());

        // Redirect 시, URL에 'sno' 값을 포함하여 리다이렉트합니다.
        return "shipping/read";
    }

    @PostMapping("/shipping/remove")
    public String remove(@RequestParam("sno") long sno, RedirectAttributes redirectAttributes){

        log.info("sno" + sno);

        shippingService.remove(sno);

        redirectAttributes.addFlashAttribute("msg", sno);

        return "shipping/shipping";
    }


    /*chat 구현*/
    private final ChatService chatService;

    @RequestMapping("mypage/chatList")
    public String chatList(Model model){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        return "mypage/chatList";
    }

    @PostMapping("mypage/createRoom")
    public String createRoom(Model model, String username, Principal principal) {
        if (principal != null) {
            String userId = principal.getName();
            Member member = memberRepository.findByMid(userId);

            log.info("유저 아이디: " + userId);

            model.addAttribute("member", member);

            // 사용자의 아이디를 사용하여 ChatService의 createRoom 메서드 호출
            ChatRoom room = chatService.createRoom(userId);

            model.addAttribute("room", room);
            model.addAttribute("username", username);

            return "mypage/chatRoom";  // 만든 사람이 채팅방 1빠로 들어가게 됩니다
        } else {
            return "member/login";
        }
    }

    @GetMapping("mypage/chatRoom")
    public String chatRoom(Model model, @RequestParam String roomId, Principal principal){

        if(principal != null){

            String mid = principal.getName();
            Member member = memberRepository.findByMid(mid);

            log.info("유저 아이디 : " + principal.getName());

            model.addAttribute("member", member);
        }else{
            return "/member/login";
        }

        ChatRoom room = chatService.findRoomById(roomId);
        model.addAttribute("room",room);//현재 방에 들어오기위해서 필요한데...... 접속자 수 등등은 실시간으로 보여줘야 돼서 여기서는 못함
        return "mypage/chatRoom";
    }




}
