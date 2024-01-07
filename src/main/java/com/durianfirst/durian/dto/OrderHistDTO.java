package com.durianfirst.durian.dto;


import com.durianfirst.durian.constaint.OrderStatus;
import com.durianfirst.durian.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class OrderHistDTO {

    private Long orderId;       //주문아이디
    private String orderDate;   //주문날짜
    private OrderStatus orderStatus; //주문상태

    private MemberJoinDTO memberDTO;

    private int ordertotalPrice;
    //주문 상품 리스트
    private List<OrderItemDTO> orderItemDtoList = new ArrayList<>();

    public OrderHistDTO(Order order){
        this.orderId = order.getOno();
        this.orderDate = order.getOdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOstatus();
        this.ordertotalPrice = order.getTotalPrice();
        this.memberDTO = new MemberJoinDTO();
    }

    public void addOrderItemDto(OrderItemDTO orderItemDto){
        orderItemDtoList.add(orderItemDto);
    }
}
