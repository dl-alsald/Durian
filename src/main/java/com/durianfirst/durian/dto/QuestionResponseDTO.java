package com.durianfirst.durian.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionResponseDTO {

    private final Long id;
    private final String title;

}
