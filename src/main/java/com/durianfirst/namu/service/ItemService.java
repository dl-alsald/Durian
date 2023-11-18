package com.durianfirst.namu.service;


import com.durianfirst.namu.dto.ItemDTO;
import com.durianfirst.namu.dto.ItemImgDTO;
import com.durianfirst.namu.dto.PageRequestDTO;
import com.durianfirst.namu.dto.PageResultDTO;
import com.durianfirst.namu.entity.Item;
import com.durianfirst.namu.entity.ItemImg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ItemService {

    Long register(ItemDTO itemDTO);

    ItemDTO read(Long ino);

    void remove(Long ino);

    void modify(ItemDTO itemDTO);

    PageResultDTO<ItemDTO, Object[]> getList(PageRequestDTO requestDTO);

    default Map<String, Object> dtoToEntity(ItemDTO itemDTO) {

        Map<String, Object> entityMap = new HashMap<>();

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

        entityMap.put("itme", item);

        List<ItemImgDTO> imageDTOList = itemDTO.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<ItemImg> itemImageList = imageDTOList.stream().map(itemImgDTO -> {

                ItemImg itemImg = ItemImg.builder()
                        .path(itemImgDTO.getPath())
                        .imgName(itemImgDTO.getImgName())
                        .uuid(itemImgDTO.getUuid())
                        .item(item)
                        .build();
                return itemImg;
            }).collect(Collectors.toList());

            entityMap.put("imgList", itemImageList);
        }
        return entityMap;
    }

    default ItemDTO entityToDTO(Item item, List<ItemImg> itemImgList) {

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

        List<ItemImgDTO> imageDTOList = itemImgList.stream().map(itemImg -> {
            return ItemImgDTO.builder()
                    .path(itemImg.getPath())
                    .imgName(itemImg.getImgName())
                    .uuid(itemImg.getUuid())
                    .build();
        }).collect(Collectors.toList());

        itemDTO.setImageDTOList(imageDTOList);

        return itemDTO;
    }
}

