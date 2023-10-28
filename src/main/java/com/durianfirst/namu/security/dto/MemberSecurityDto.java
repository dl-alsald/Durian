package com.durianfirst.namu.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberSecurityDto extends User {

    private Long mno;
    private String mid; //아이디
    private String mpassword; //비밀번호
    private String mname; //이름
    private String memail; //이메일
    private String mbirthday; //생년월일
    private String maddress; //주소
    private String mphonenum; //전화번호
    private Boolean mnational; //내,외국인 구분
    /* 소셜 로그인 */
    private Boolean msocial; //소셜로그인 여부
    /* 그 외 */
    private Boolean mdel; //회원탈퇴 여부
    private String merecommend; //추천인 아이디

    public MemberSecurityDto(Long mno, String username, String password, String mname, String memail,
                             String mbirthday, String maddress, String mphonenum, Boolean mnational,
                             Boolean msocial, Boolean mdel, String merecommend,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.mno = mno;
        this.mid = username;
        this.mpassword = password;
        this.mname = mname;
        this.memail = memail;
        this.mbirthday = mbirthday;
        this.maddress = maddress;
        this.mphonenum = mphonenum;
        this.mnational = mnational;
        this.msocial = msocial;
        this.mdel = mdel;
        this.merecommend = merecommend;
    }
}
