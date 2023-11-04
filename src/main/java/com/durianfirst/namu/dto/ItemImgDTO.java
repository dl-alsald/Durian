package com.durianfirst.namu.dto;

import com.durianfirst.namu.entity.ItemImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemImgDTO {

    private Long iimgno;

    private String iimgnew;

    private String iigori;

    private String iimgurl;

    private String iimgrep;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDTO of(ItemImg itemImg) {
        // ItemImg 객체로 받아 ItemImg 객체의 자료형과 멤버변수의 이름이 같을 때 ItemImgDto로 값을 복사해서 반환
        return modelMapper.map(itemImg, ItemImgDTO.class);
    }

}
