package com.durianfirst.durian.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberJoinDTO {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Length(min = 4, max =15 ,message ="아이디는 4자 이상, 15자 이하로 입력해주세요" )
    private String mid;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String mpw;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String memail;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String mname;

    @NotBlank(message = "핸드폰 번호는 필수 입력 값입니다.")
    private String mphone;

    @NotBlank(message = "생일은 필수 입력 값입니다.")
    private String mbirthday;

    private String maddress;
/*    private String mnational;
    private String mrecommend;*/

    private boolean mdel;
    private boolean msocial;
}