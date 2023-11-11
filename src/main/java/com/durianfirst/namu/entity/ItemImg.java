package com.durianfirst.namu.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="itemimage")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "item")
public class ItemImg extends BaseEntity{

    @Id
    @Column(name="iimgno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String uuid;

   private String imgName;

   private String path;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Item item;



}