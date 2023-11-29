package com.durianfirst.namu.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "point")
@Data
public class Point extends BaseEntity {

    @Id
    private Long pid; //포인트 아이디

    @ManyToOne
    private Member member; //회원정보

    private int pbalance; // 잔액


}
