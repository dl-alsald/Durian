package com.durianfirst.durian.entity;


import com.durianfirst.durian.constaint.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="member")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member extends BaseEntity {

    /* 회원 정보 */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mno;

    @Id
    private String mid; //아이디

    private String mpw; //비밀번호

    private String mname; //이름

    private String memail; //이메일

    private String mbirthday; //생년월일

    private String maddress; //주소

    private String mphone; //전화번호

    private boolean mnational; //내,외국인 구분

    private boolean msocial; //소셜로그인 여부

    private boolean mdel; //회원탈퇴 여부

    private String mrecommend; //추천인 아이디

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<MemberImg> memberImgs;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String mpw){
        this.mpw = mpw;
    }

    public void changeEmail(String memail){
        this.memail = memail;
    }

    public void changeDel(boolean mdel){
        this.mdel = mdel;
    }

    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles(){
        this.roleSet.clear();
    }

    public void changeSocial(boolean msocial){
        this.msocial = msocial;
    }

    public void update(String mname, String memail, String mbirthday, String mphone, String mpw){
        this.mname = mname;
        this.memail = memail;
        this.mbirthday = mbirthday;
        this.mphone = mphone;
        this.mpw = mpw;
    }

}