package com.durianfirst.durian.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notice")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice extends BaseEntity{

    @Id
    @Column(name = "nno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nno;

    @Column(nullable = false)
    private String ntitle;

    @Lob
    @Column(nullable = false, length = 5000)
    private String ncontent;

    private LocalDateTime regDate, modDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member; //작성자

    public void changeTitle(String ntitle){
        this.ntitle = ntitle;
    }

    public void changeContent(String ncontent){
        this.ncontent = ncontent;
    }

}
