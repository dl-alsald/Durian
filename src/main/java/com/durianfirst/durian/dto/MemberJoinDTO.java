package com.durianfirst.durian.dto;

import com.durianfirst.durian.entity.Member;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MemberJoinDTO {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 5, max = 10, message = "아이디는 5 ~ 10자 사이로 입력해주세요")
    private String mid;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String mpw;

    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String memail;

    private boolean mdel;
    private boolean msocial;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String mname;
    private String mbirthday;
    private String maddress;
    private String mphone;
    /*private String mnational;
    private String mrecommend;*/

    public Member toEntity(){
        Member member = Member.builder()
                .mid(mid)
                .mname(mname)
                .memail(memail)
                .mbirthday(mbirthday)
                .mphone(mphone)
                .build();

        return member;
    }

}
