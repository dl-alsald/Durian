package com.durianfirst.durian.controller;

import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Answer;
import com.durianfirst.durian.entity.AnswerForm;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.entity.Question;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.service.AnswerService;
import com.durianfirst.durian.service.MemberService;
import com.durianfirst.durian.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
@Log4j2
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    private final QuestionService questionService;

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/answer/create")
    public String create(Long qno,Principal principal, PageRequestedDTO pageRequestedDTO, Model model) {

        Question question = this.answerService.getQuestion(qno);
        model.addAttribute("question", question);

        QuestionDTO questionDTO = answerService.create(qno);

        log.info(questionDTO);

        model.addAttribute("dto", questionDTO);

        return "answer/create";
    }

    @GetMapping("/answer/list2")
    public void list2(PageRequestedDTO pageRequestedDTO, Model model) {

        PageResponsedDTO<QuestionDTO> responseDTO = questionService.list(pageRequestedDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);


    }
    @PostMapping("/answer/createa/{qno}")
    public String createAnswer(Model model, @PathVariable("qno") Long qno, @Valid AnswerForm answerForm,
                               BindingResult bindingResult, Principal principal) {
        Question question = this.answerService.getQuestion(qno);
        Member member = this.memberService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "answer/create";
        }

        Answer answer = this.answerService.createa(question, answerForm.getAcontent(), member);

        return String.format("redirect:/answer/create?qno=" + qno,
                answer.getAquestion().getQno(), answer.getAno());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answer/modify/{ano}")
    public String answerModify(AnswerDTO answerDTO, @PathVariable("ano") Long ano, Principal principal) {
        // 주어진 ID에 해당하는 Answer를 가져옵니다.
        Answer answer = this.answerService.getAnswer(ano);

        // 현재 사용자가 해당 Answer의 작성자인지 확인합니다.
        if (!answer.getMember().getMid().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        // AnswerForm에 수정할 내용을 설정합니다.
        answerDTO.setAcontent(answer.getAcontent());

        // 수정 화면(answer_form)으로 이동합니다.
        return "answer/modify";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/answer/modify/{ano}")
    public String answerModify(@Valid AnswerDTO answerDTO, BindingResult bindingResult,
                               @PathVariable("ano") Long ano, Principal principal) {
        if (bindingResult.hasErrors()) {

        }
        Answer answer = this.answerService.getAnswer(ano);
        if (!answer.getMember().getMid().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.modify(answer, answerDTO.getAcontent());
        return String.format("redirect:/answer/create?qno=%s",  answer.getAquestion().getQno());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answer/delete/{ano}")
    public String answerDelete(Principal principal, @PathVariable("ano") Long ano) {
        Answer answer = this.answerService.getAnswer(ano);
        if (!answer.getMember().getMid().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.answerService.delete(answer);


        /*  return String.format("redirect:/answer/create/{qno}");*/
        return String.format("redirect:/answer/create?qno=%s", answer.getAquestion().getQno());


    }
}


