package com.durianfirst.durian.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {


    private Long eno;
    private String etitle;
    private String econtent;
    private LocalDateTime regDate, modDate;

    @Builder.Default
    private List<EventImageDTO> imageDTOList = new ArrayList<>(); //제품이미지



}
