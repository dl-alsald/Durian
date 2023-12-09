package com.durianfirst.namu.dto;

import com.durianfirst.namu.constant.OrderStatus;
import com.durianfirst.namu.entity.Member;
import com.durianfirst.namu.entity.Order;
import com.durianfirst.namu.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistDTO {

    private Long orderId;       //주문아이디
    private String orderDate;   //주문날짜
    private OrderStatus orderStatus; //주문상태

    private MemberDto memberDTO;

    private int ordertotalPrice;
    //주문 상품 리스트
    private List<OrderItemDTO> orderItemDtoList = new ArrayList<>();

    public OrderHistDTO(Order order){
        this.orderId = order.getOno();
        this.orderDate = order.getOdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOstatus();
        this.ordertotalPrice = order.getTotalPrice();
        this.memberDTO = new MemberDto();
    }

    public void addOrderItemDto(OrderItemDTO orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
}
