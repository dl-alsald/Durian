package com.durianfirst.namu.dto;

import com.durianfirst.namu.constant.ItemSellStatus;
import com.durianfirst.namu.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long ino;

    private String iname;

    private int iprice;

    private String idealway;

    private String ilocation;

    private String icate;

    private String icondition;

    private String idesc;

    private ItemSellStatus istatus;

    private LocalDateTime regDate; //등록일

    private LocalDateTime modDate; //수정일

    @Builder.Default
    private List<ItemImgDTO> imageDTOList = new ArrayList<>(); //제품이미지

    private List<Long> itemImgIds = new ArrayList<>(); // 상품의 이미지 아이디를 저장하는 리스트

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() { // 엔티티 객체의 데이터를 복사하여 복사한 객체를 반환해줌
        return modelMapper.map(this, Item.class);
    }

    public static ItemDTO of(Item item){ // dto 객체의 데이터를 복사하여 복사한 객체를 반환해줌
        return modelMapper.map(item, ItemDTO.class);
    } // modelMapper 이용

}
