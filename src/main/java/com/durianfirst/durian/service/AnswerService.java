package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.QuestionDTO;
import com.durianfirst.durian.entity.Answer;
import com.durianfirst.durian.entity.Question;

public interface AnswerService {


    QuestionDTO create(Long qno);

    Question getQuestion(Long qno);

    void createa(Question question, String acontent);

    Answer getAnswer(Long qno);

    void modify(Answer answer, String acontent);

/*    void delete(Answer answer);*/

    public void delete(Long ano);

    }
