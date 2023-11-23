package com.durianfirst.namu.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class MemberSecurityDto extends User implements OAuth2User {

    private String mid; //아이디
    private String mpw; //비밀번호
    private String mname; //이름
    private String memail; //이메일
    private String mbirthday; //생년월일
    private String maddress; //주소
    private String mphone; //전화번호
    private Boolean mnational; //내,외국인 구분
    /* 소셜 로그인 */
    private Boolean msocial; //소셜로그인 여부
    /* 그 외 */
    private Boolean mdel; //회원탈퇴 여부
    private String merecommend; //추천인 아이디

    private Map<String, Object> props;  //소셜 로그인 정보

    public MemberSecurityDto(String mid, String mpassword, String mname, String memail,
                             String mbirthday, String maddress, String mphone, Boolean mnational,
                             Boolean msocial, Boolean mdel, String merecommend,
                             Collection<? extends GrantedAuthority> authorities) {
        super(mid, mpassword, authorities);
        this.mid = mid;
        this.mpw = mpassword;
        this.mname = mname;
        this.memail = memail;
        this.mbirthday = mbirthday;
        this.maddress = maddress;
        this.mphone = mphone;
        this.mnational = mnational;
        this.msocial = msocial;
        this.mdel = mdel;
        this.merecommend = merecommend;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.getProps();
    }

    @Override
    public String getName() {
        return this.mid;
    }

}
