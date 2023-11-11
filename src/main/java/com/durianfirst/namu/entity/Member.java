package com.durianfirst.namu.entity;

import com.durianfirst.namu.role.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="member")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleset")
public class Member extends BaseEntity {

    /* 회원 정보 */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long mno;

    @Id
    String mid; //아이디

    String mpw; //비밀번호

    String mname; //이름

    String memail; //이메일

    String mbirthday; //생년월일

    String maddress; //주소

    String mphone; //전화번호

    Boolean mnational; //내,외국인 구분

    /* 소셜 로그인 */
    Boolean msocial; //소셜로그인 여부

    /* 그 외 */
    Boolean mdel; //회원탈퇴 여부

    String merecommend; //추천인 아이디

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


}
