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
public class ItemImg extends BaseEntity {

    @Id
    @Column(name = "iimgno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String imgName;

    private String path;

    private String iimgrep; //대표 이미지 여부


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ino")
    private Item item;


    public void updateItemImg(String uuid, String imgName, String path) {
        // 원본 이미지 파일명, 업데이트할 이미지 파일명, 이미지 경로를 파라미터로 입력받아 이미지 정보를 업데이트
        this.uuid = uuid;
        this.imgName = imgName;
        this.path = path;

    }
}