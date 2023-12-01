package com.durianfirst.durian.service;

import com.durianfirst.durian.constant.MemberRole;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.namu.dto.MemberJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(MemberJoinDTO memberJoinDTO) throws MidExistException{ //mid가 존재하는 경우 MidExistException을 발생
        String mid = memberJoinDTO.getMid();

        boolean exist = memberRepository.existsById(mid); //mid가 유일한지 체크하고 문제가 생기면 MidExistException을 발생시킴

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        //정상적으로 회원 가입이 된 경우 PasswordEncoder를 이용해서 입력된 패스워드를 인코딩
        member.addRole(MemberRole.USER);

        log.info("==============================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member); //해당 아이디가 존재하면 MemberRepository의 save()는 insert가 아니라 update로 실행

    }


}
