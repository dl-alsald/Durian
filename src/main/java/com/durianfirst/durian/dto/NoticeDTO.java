package com.durianfirst.durian.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {

    private Long nno;

    @NotEmpty
    private String ntitle;
    private String ncontent;
    private LocalDateTime regDate, modDate;



}
