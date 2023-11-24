package com.durianfirst.namu.repository;


import com.durianfirst.namu.entity.Member;
import com.durianfirst.namu.refository.MemberRepository;
import com.durianfirst.namu.role.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers(){
        IntStream.rangeClosed(1,10).forEach(i -> {

            Member member = Member.builder()
                    .mid("merber"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .memail("email"+i+"@aaa.aaa")
                    .mname("user"+i)
                    .maddress("address"+i)
                    .mbirthday("1990"+i)
                    .mphone("null")
                    .build();
            member.addRole(MemberRole.USER);

            if( i > 7){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){
        Optional<Member> result = memberRepository.getWithRoles("member10");
        result.ifPresent(member -> {
            log.info(member);
            log.info(member.getRoleSet());

            member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
        });
}

    @Commit
    @Test
    public void testUpdate(){
        String mid = "eamil1@aaa.aaa"; //소셜로그인으로 추가된 사용자롤 현재 DB에 존재하는 이메일
        String mpw = passwordEncoder.encode("54321");

        memberRepository.updatePassword(mpw,mid);
}
}

