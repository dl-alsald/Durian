package com.durianfirst.durian.dto;

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
    /*private String mnational;
    private String mrecommend;*/

}
