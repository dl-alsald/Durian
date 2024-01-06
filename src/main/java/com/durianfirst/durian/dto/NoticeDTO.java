package com.durianfirst.durian.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {

    private Long nno;

    @NotBlank(message = "제목을 입력해주세요")
    private String ntitle;

    @NotBlank(message = "내용을 입력해주세요")
    private String ncontent;

    private LocalDateTime regDate, modDate;

}
