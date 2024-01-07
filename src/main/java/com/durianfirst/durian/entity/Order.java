package com.durianfirst.durian.entity;

import com.durianfirst.durian.constaint.OrderStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = {"item","member"})
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private LocalDateTime odate; //주문일

//    @Enumerated(EnumType.STRING)
//    private OrderStatus ostatus; //주문상태

    private int totalPrice; //결제금액

//    public void cancelOrder(){
//        this.ostatus = ostatus.CANCEL;
//
//    }

}