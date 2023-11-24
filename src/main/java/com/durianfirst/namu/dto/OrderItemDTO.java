package com.durianfirst.namu.dto;

@Getter
@Setter
public class OrderItemDTO {

    //상품 페이지에서 주문할 상품의 아이디와 주문 수량을 전달받을 DTO

    private String itemNm; //상품명
    private int count;  //주문 수량
    private int orderPrice; //주문 금액
    private String imgUrl;  //상품이미지 경로

    public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getPname();
        this.count = orderItem.getOiquantity();
        this.orderPrice = orderItem.getOiprice();
        this.imgUrl = imgUrl;
    }
}
