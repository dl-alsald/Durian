package com.durianfirst.durian.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Heart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hno;

    @ManyToOne(fetch = FetchType.EAGER)
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member member;

    /*private int pno;
    private String mid;*/

    public Heart(Item item, Member member){
        this.item = item;
        this.member = member;
    }

}
