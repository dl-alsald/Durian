package com.durianfirst.durian.dto;

import com.durianfirst.durian.entity.Member;
import lombok.Data;

@Data
public class MemberJoinDTO {

    private String mid;
    private String mpw;
    private String memail;
    private boolean mdel;
    private boolean msocial;

    private String mname;
    private String mbirthday;
    private String maddress;
    private String mphone;
    private String mnational;
    private String mrecommend;

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
