package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.PageRequestedDTO;
import com.durianfirst.durian.dto.PageResponseDTO;
import com.durianfirst.durian.dto.PageResponsedDTO;
import com.durianfirst.durian.dto.QuestionDTO;
import com.durianfirst.durian.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.durianfirst.durian.entity.Question;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper modelMapper;

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> getList() {
        return this.questionRepository.findAll();

    }

    @Override
    public Long register(QuestionDTO questionDTO) {

        Question question = modelMapper.map(questionDTO, Question.class);

        Long qno = questionRepository.save(question).getQno();

        return qno;
    }

    @Override
    public QuestionDTO readOne(Long qno) {

        Optional<Question> result = questionRepository.findById(qno);

        Question question = result.orElseThrow();

        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);

        return questionDTO;
    }

    @Override
    public void modify(QuestionDTO questionDTO) {

        Optional<Question> result = questionRepository.findById(questionDTO.getQno());

        Question question = result.orElseThrow();

        question.change(questionDTO.getQtitle(), questionDTO.getQcontent());

        questionRepository.save(question);
    }

    @Override
    public void remove(Long qno) {

        questionRepository.deleteById(qno);
    }

    @Override
    public PageResponsedDTO<QuestionDTO> list(PageRequestedDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("qno");

        Page<Question> result = questionRepository.searchAll(types, keyword, pageable);

        List<QuestionDTO> dtoList = result.getContent().stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class)).collect(Collectors.toList());


        return PageResponsedDTO.<QuestionDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    }
