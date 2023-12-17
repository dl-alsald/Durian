package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.PageRequestedDTO;
import com.durianfirst.durian.dto.PageResponsedDTO;
import com.durianfirst.durian.dto.QuestionDTO;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.entity.Question;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.service.AnswerService;
import com.durianfirst.durian.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
/*@RequestMapping("/")*/
@Log4j2
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberRepository memberRepository;

    @GetMapping("/question/list")
    public void list(PageRequestedDTO pageRequestDTO, Model model) {

        PageResponsedDTO<QuestionDTO> responseDTO = questionService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping("/question/list2")
    public void list2(PageRequestedDTO pageRequestDTO, Model model) {

        PageResponsedDTO<QuestionDTO> responseDTO = questionService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping("/question/register") //등록처리
    public String registerGET(Principal principal) {

        if(principal != null){

            String mid = principal.getName();
            Member member = memberRepository.findBymid(mid);

            log.info("유저 아이디 : " + principal.getName());

        }else {
            return "member/login";
        }
        return "question/register";
    }

    @PostMapping("/question/register")
    public String registerPost(@Valid QuestionDTO questionDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) { //@Valid에서 문제 시 모든 에러를 errors 이름으로 redirectAttributes.addAttribute에 추가, 전송
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/question/register";
        }
        Long qno = questionService.register(questionDTO);

        redirectAttributes.addFlashAttribute("result", qno);

        return "redirect:/question/list";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping({"/question/read", "/question/modify"})
    public void read(Long qno, PageRequestedDTO pageRequestDTO, Model model) {

        QuestionDTO questionDTO = questionService.readOne(qno);

        log.info(questionDTO);

        model.addAttribute("dto", questionDTO);
    }

    @PreAuthorize("principal.username == #questionDTO.member.mid")
    @PostMapping("/question/modify")
    public String modify(PageRequestedDTO pageRequestDTO,
                         @Valid QuestionDTO questionDTO,
                         BindingResult bindingResult, //유효성검사저장
                         RedirectAttributes redirectAttributes) {

        log.info("question modify post......" + questionDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors.....");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("qno", questionDTO.getQno());

            return "redirect:/question/modify?" + link;//수정시 문제있을때 수정페이지로
        }

        questionService.modify(questionDTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("qno", questionDTO.getQno());

        return "redirect:/question/read"; //수정시 문제없을시
    }

    @PostMapping("/question/remove")
    public String remove(Long qno, RedirectAttributes redirectAttributes) {

        log.info("remove post.. " + qno);

        questionService.remove(qno);

        redirectAttributes.addFlashAttribute("result", "remove");

        return "redirect:/question/list";
    }
    /*-----------------답변*/
    @GetMapping("/question/list3")
    public void list3(PageRequestedDTO pageRequestDTO, Model model) {

        PageResponsedDTO<QuestionDTO> responseDTO = questionService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }
    @GetMapping("/question/answer")
    public void answer(Long qno, PageRequestedDTO pageRequestDTO, Model model) {

        Question question = this.answerService.getQuestion(qno);
        model.addAttribute("question", question);

        QuestionDTO questionDTO = answerService.create(qno);

        log.info(questionDTO);

        model.addAttribute("dto", questionDTO);

    }
    }



