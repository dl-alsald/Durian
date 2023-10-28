package com.durianfirst.namu.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="event")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event extends BaseEntity {

    @Id
    @Column(name="eno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno; //번호

    @Column(nullable = false)
    private String etitle; //제목

    @Lob
    @Column(nullable = false, length = 5000)
    private String econtent; //내용


}
