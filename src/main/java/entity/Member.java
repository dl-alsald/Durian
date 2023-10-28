package entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
@Data
public class Member {

    /* 회원 정보 */
    @Id
    String mid; //아이디

    String mpassword; //비밀번호

    String mname; //이름

    String memail; //이메일

    String mbirthday; //생년월일

    String maddress; //주소

    String mphonenum; //전화번호

    Boolean mnational; //내,외국인 구분

    /* 소셜 로그인 */
    Boolean msocial; //소셜로그인 여부

    /* 그 외 */
    Boolean mdel; //회원탈퇴 여부

    String merecommend; //추천인 아이디




}
