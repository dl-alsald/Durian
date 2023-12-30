package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.*;
import com.durianfirst.durian.entity.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface QuestionService {


    public int updateView(Long qno);

    Long register(QuestionDTO questionDTO);


    QuestionDTO readOne(Long qno);


    void modify(QuestionDTO questionDTO);

    void remove(Long qno);

    PageResponsedDTO<QuestionDTO> list(PageRequestedDTO pageRequestedDTO);


    public List<Question> getList();


}


