package com.durianfirst.namu.entity;


import com.durianfirst.namu.constant.ItemSellStatus;
import com.durianfirst.namu.dto.ItemDTO;
import lombok.*;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity //클래스를 엔티티로 선언
@Table(name = "item") //엔티티와 매핑할 테이블을 지정(테이블 명)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ino;

    @Column(nullable = false) // not null 설정 및 길이 지정, nullable = false : not null
    private String iname; //상품명

    @Column(nullable = false)
    private int iprice;    //가격

    private String idealway; //거래방법

    private String ilocation; //거래장소

    @Column(nullable = false)
    private String icate;  // 카테고리

    @Column(nullable = false)
    private String icondition; //상품상태

    @Lob //BLOB, CLOB 타입 매핑
    // CLOB : 사이즈가 큰 테이터를 외부 파일로 저장하기 위한 데이터 타입 (문자형 대용량 파일 저장)
    // BLOB : 바이너리 데이터를 DB외부에 저장하기 위한 타입 (이미지, 사운드, 비디오 : 멀티미디어)
    @Column(nullable = false, length = 5000)
    private String idesc; // 상품 상세 설명

    @Enumerated(EnumType.STRING)  // enum 타입 매핑
    private ItemSellStatus istatus; //상품 판매 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mid_seller")
    private Member member;


    public void changeIname(String iname){ this.iname = iname; }
    public void changeIprice(int iprice){ this.iprice = iprice; }
    public void changeIdealway(String idealway){ this.idealway = idealway; }
    public void changeIlocateion(String ilocation) { this.ilocation = ilocation;}
    public void changeIcate(String icate){ this.icate = icate; }
    public void changeIcondition(String icondition){ this.icondition = icondition; }
    public void changeIdesc(String idesc){ this.idesc = idesc; }
    public void changeIstatus(ItemSellStatus istatus){ this.istatus = istatus; }

}