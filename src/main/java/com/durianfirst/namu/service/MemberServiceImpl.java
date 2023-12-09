package com.durianfirst.namu.service;

import com.durianfirst.namu.dto.MemberDto;
import com.durianfirst.namu.entity.Member;
import com.durianfirst.namu.repository.MemberRepository;
import com.durianfirst.namu.role.MemberRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void join(MemberDto memberDto) throws MidExistException {

        String mid = memberDto.getMid();

        boolean exist = memberRepository.existsById(Long.valueOf(mid));

        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberDto,Member.class);
        member.changePassword(passwordEncoder.encode(memberDto.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("===================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);

    }
}
