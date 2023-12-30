package com.durianfirst.durian.dto;

import com.durianfirst.durian.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long qno; //질문 번호(pk)

    @NotEmpty(message = "제목은 필수항목입니다.") /*Null허용치않음*/
    @Column(nullable = false)
    private String qtitle; //질문제목

    @NotEmpty(message = "질문내용은 필수항목입니다.")
    @Column(nullable = false, length = 5000)
    private String qcontent; //질문내용

    @NotBlank(message = "카테고리선택은 필수항목입니다.")
    @Column(nullable = false)
    private String qcate; //카테고리

    private Member member; //작성자

    private String qmember;

    private int view;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}


