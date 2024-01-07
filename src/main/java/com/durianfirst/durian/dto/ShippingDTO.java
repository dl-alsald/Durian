package com.durianfirst.durian.dto;

import com.durianfirst.durian.entity.Shipping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShippingDTO<E> {

    private Long sno;

    private String mid;         // 회원 DB와 연결

    private String sname;       // 배송지명

    private String sperson;     // 받는 분

    private String szonecode;    // 우편번호

    private String address;     // 주소

    private String saddress;    // 상세 주소

    private String sphone;   // 휴대폰 번호

    private Boolean sdefault;

    private List<E> dtoList;

    private static ModelMapper modelMapper = new ModelMapper();

    public Shipping createItem() { // 엔티티 객체의 데이터를 복사하여 복사한 객체를 반환해줌
        return modelMapper.map(this, Shipping.class);
    }

    public static ShippingDTO of(Shipping shipping){ // dto 객체의 데이터를 복사하여 복사한 객체를 반환해줌
        return modelMapper.map(shipping, ShippingDTO.class);
    }

}
