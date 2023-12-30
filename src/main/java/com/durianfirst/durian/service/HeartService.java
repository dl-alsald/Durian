package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.HeartDTO;
import com.durianfirst.durian.entity.Heart;
import com.durianfirst.durian.entity.Member;

import java.util.List;

public interface HeartService {

    boolean addHeart(Member member, Long ino);
    void cancelHeart(Member member,Long ino);
    List<String> count(Long ino, Member loginMember);




    /*Long register(HeartDTO heartDTO);*/

    /*HeartDTO read(Long hno);

    void remove(Long hno);

    void modify(HeartDTO dto);

    default Heart dtoToEntity(HeartDTO heartDTO) {
        Heart entity = Heart.builder()
                .hno(heartDTO.getHno())
                .item(heartDTO.getIno())
                .mid(heartDTO.getMid())
                .build();
        return entity;
    }

    default HeartDTO entityToDto(Heart entity) {

        HeartDTO heartDTO = HeartDTO.builder()
                .hno(entity.getHno())
                .pno(entity.getPno())
                .mid(entity.getMid())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return heartDTO;
    }*/
}
