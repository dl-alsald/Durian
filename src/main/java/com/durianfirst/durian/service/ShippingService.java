package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.PageRequestDTO;
import com.durianfirst.durian.dto.PageResultDTO;
import com.durianfirst.durian.dto.ShippingDTO;
import com.durianfirst.durian.entity.Shipping;

import java.util.List;

public interface ShippingService {

    Long register(ShippingDTO ShippingDTO);

    void updateDefaultShipping(String mid);

    // 조회 (아이디 별로 전체)
    List<ShippingDTO> readAll(String mid);

    PageResultDTO<ShippingDTO, Shipping> getList(PageRequestDTO requestDTO);


    // 조회 (해당 주소 1개)
    ShippingDTO readOne(Long sno);

    // 수정
    void modify(ShippingDTO shippingDTO);

    // 삭제
    void remove(Long sno);

    ShippingDTO getDefaultAddress(String mid);

    // 기본배송지 수정
    void modifySdefault(String mid);
}
