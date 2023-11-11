package com.durianfirst.namu.security.handler;

import com.durianfirst.namu.entity.Member;
import com.durianfirst.namu.refository.MemberRepository;
import com.durianfirst.namu.role.MemberRole;
import com.durianfirst.namu.security.dto.MemberSecurityDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        log.info("userRequest....");
        log.info(userRequest);

        log.info("oauth2 user...................");

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();

        log.info("NAME : "+clientName);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String,Object> paramMap = oAuth2User.getAttributes();

        String memail = null;

        switch (clientName){
            case "kakao":
                memail = getKakaoMemail(paramMap);
                break;
        }

        log.info("======================");
        log.info(memail);
        log.info("======================");

        return generateDTO(memail,paramMap);
    }

    private MemberSecurityDto generateDTO(String memail, Map<String, Object> params ){

        Optional<Member> result = memberRepository.findByMemail(memail);

        //데이터베이스에 해당 이메일 사용자가 없다면
        if(result.isEmpty()){
            //회원추가 -- mid는 이메일 /
            Member member = Member.builder()
                    .mid(memail)
                    .mpw(passwordEncoder.encode("1111"))
                    .memail(memail)
                    .msocial(true)
                    .mnational(true)
                    .build();
            member.addRole(MemberRole.USER);
            memberRepository.save(member);

            //MemberSecurityDTO구성반환
            MemberSecurityDto memberSecurityDto = new MemberSecurityDto(memail,"1111",null,
                    memail, null,null,null,null,true,
                    null,null,
                    Arrays.asList(new SimpleGrantedAuthority("Role_USER")));
            memberSecurityDto.setProps(params);

            return memberSecurityDto;
        }else{
            Member member = result.get();
            MemberSecurityDto memberSecurityDto =
                    new MemberSecurityDto(
                            member.getMid(),
                            member.getMpw(),
                            member.getMname(),
                            member.getMemail(),
                            member.getMbirthday(),
                            member.getMaddress(),
                            member.getMphone(),
                            member.getMnational(),
                            member.getMsocial(),
                            member.getMdel(),
                            member.getMerecommend(),
                            member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                                    .collect(Collectors.toList())
                    );
            return memberSecurityDto;
        }
    }

    private String getKakaoMemail(Map<String,Object> paramMap){
        log.info("KAKAO----------------------------");

        Object value = paramMap.get("kakao_account");

        log.info(value);

        LinkedHashMap accountMap = (LinkedHashMap) value;

        String memail = (String) accountMap.get("memail");

        log.info("mname..."+ memail);

        return memail;
    }
}
