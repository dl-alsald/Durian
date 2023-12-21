package com.durianfirst.durian.validator;

import com.durianfirst.durian.dto.MemberJoinDTO;
import com.durianfirst.durian.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
@RequiredArgsConstructor
public class CheckValidator extends AbstractValidator<MemberJoinDTO> {


    private MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberJoinDTO dto, Errors errors) {
        if(memberRepository.existsByMemail(dto.getMemail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
        if(memberRepository.existsByMid(dto.getMid())) {
            errors.rejectValue("ID", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
