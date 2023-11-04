package com.durianfirst.namu.service;


import com.durianfirst.namu.dto.ItemDTO;
import com.durianfirst.namu.dto.ItemImgDTO;
import com.durianfirst.namu.dto.PageRequestDTO;
import com.durianfirst.namu.dto.PageResultDTO;
import com.durianfirst.namu.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemService {

    Long register(ItemDTO itemDTO);

    ItemDTO read(Long ino);

    void remove(Long ino);

    void modify(ItemDTO itemDTO);

    PageResultDTO<ItemDTO, Item> getList(PageRequestDTO requestDTO);

    default Item dtoToEntity(ItemDTO itemDTO){
        Item item = Item.builder()
                .ino(itemDTO.getIno())
                .iname(itemDTO.getIname())
                .iprice(itemDTO.getIprice())
                .idealway(itemDTO.getIdealway())
                .ilocation(itemDTO.getIlocation())
                .icate(itemDTO.getIcate())
                .icondition(itemDTO.getIcondition())
                .idesc(itemDTO.getIdesc())
                .istatus(itemDTO.getIstatus())
                .build();
        return item;
    }

    default ItemDTO entityToDTO(Item item){

        ItemDTO itemDTO = ItemDTO.builder()
                .ino(item.getIno())
                .iname(item.getIname())
                .iprice(item.getIprice())
                .idealway(item.getIdealway())
                .ilocation(item.getIlocation())
                .icate(item.getIcate())
                .icondition(item.getIcondition())
                .idesc(item.getIdesc())
                .istatus(item.getIstatus())
                .regDate(item.getRegTime())
                .modDate(item.getUpdateTime())
                .build();

        return itemDTO;

    }



}
