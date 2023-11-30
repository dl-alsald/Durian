package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Question;
import com.durianfirst.durian.service.AnswerService;
import com.durianfirst.durian.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    private final QuestionService questionService;

    @GetMapping("/answer/create")
    public void create(Long qno, PageRequestedDTO pageRequestDTO, Model model) {

        Question question = this.answerService.getQuestion(qno);
        model.addAttribute("question", question);

        QuestionDTO questionDTO = answerService.create(qno);

        log.info(questionDTO);

        model.addAttribute("dto", questionDTO);

    }

    @GetMapping("/answer/list2")
    public void list2(PageRequestedDTO pageRequestDTO, Model model) {

        PageResponsedDTO<QuestionDTO> responseDTO = questionService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);


    }


        @PostMapping("/answer/createa/{qno}")

    public String createAnswer(Model model, @PathVariable("qno") Long qno,
                               @RequestParam String acontent) {
        Question question = this.answerService.getQuestion(qno);
        this.answerService.createa(question, acontent);

        return "redirect:/answer/list2";

        /* return String.format("redirect:/answer/create/%s", qno);*/
    }


    @PostMapping("/answer/delete/{ano}")
    public String delete(Long ano, RedirectAttributes redirectAttributes) {

        log.info("remove post.. " + ano);

        answerService.delete(ano);

        redirectAttributes.addFlashAttribute("result", "delete");

        return "redirect:/answer/list2";
    }

}
