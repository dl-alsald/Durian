package com.durianfirst.durian.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {

    private Long ino;

    private String iname;

    private int iprice;

    private String isaleStatus;

    private String icategory;

    private String idealway;

    private String ilocation;

    private String idescription;

    private String icondition;

    private String mid;

    private LocalDateTime regDate; //등록일

    private LocalDateTime modDate; //수정일

    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>(); //제품이미지

    public ItemDTO(Long ino, String iname, int iprice, String isaleStatus, String icategory, String idealway, String idescription, String ilocation, String icondition, String mid) {
        this.ino = ino;
        this.iname = iname;
        this.iprice = iprice;
        this.isaleStatus = isaleStatus;
        this.icategory = icategory;
        this.idealway = idealway;
        this.idescription = idescription;
        this.ilocation = ilocation;
        this.icondition = icondition;
        this.mid= mid;

    }



}
