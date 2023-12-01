package com.durianfirst.namu.dto;

import lombok.Data;

@Data
public class MemberJoinDTO {

    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;

    private String name;
    private String birthday;
    private String address;
    private String phone;
    private String national;
    private String recommend;

}
