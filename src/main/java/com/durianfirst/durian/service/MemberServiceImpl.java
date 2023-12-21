package com.durianfirst.durian.service;

import com.durianfirst.durian.constaint.MemberRole;
import com.durianfirst.durian.dto.MemberImageDTO;
import com.durianfirst.durian.dto.MemberJoinDTO;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.entity.MemberImg;
import com.durianfirst.durian.repository.MemberImgRepository;
import com.durianfirst.durian.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final MemberImgRepository memberImgRepository;

    @Override
    public void register(MemberJoinDTO memberJoinDTO) throws MidExistException{ //mid가 존재하는 경우 MidExistException을 발생
        String mid = memberJoinDTO.getMid();

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        boolean exist = memberRepository.existsById(mid); //mid가 유일한지 체크하고 문제가 생기면 MidExistException을 발생시킴
        MemberImg memberImg = MemberImg.builder()
                .mimgurl("/assets/images/faces/anonymous.png")
                .member(member).build();


        if(exist){
            throw new MidExistException();
        }
        log.info("==============================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member); //해당 아이디가 존재하면 MemberRepository의 save()는 insert가 아니라 update로 실행
        memberImgRepository.save(memberImg);

    }

    @Override
    public String updateMember(MemberJoinDTO memberJoinDTO) {
        Member member = memberRepository.findByMid(memberJoinDTO.getMid());
        member.changeName(memberJoinDTO.getMname());
        member.changeEmail(memberJoinDTO.getMemail());
        member.changePhone(memberJoinDTO.getMphone());
        member.changeAddress(memberJoinDTO.getMaddress());

        // 회원 비밀번호 수정을 위한 패스워드 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePw = encoder.encode(memberJoinDTO.getMpw());
        member.changePassword(encodePw);

        memberRepository.save(member);

        return member.getMid();
    }

    @Override
    public boolean deleteMember(String mid, String mpw) {
        Member member = memberRepository.findByMid(mid);

        if (passwordEncoder.matches(mpw, member.getMpw())) {
            memberRepository.delete(member);
            return true;
        } else {
            return false;
        }
    }

}
