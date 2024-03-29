package com.durianfirst.durian.security;

import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.repository.MemberRepository;
import com.durianfirst.durian.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        log.info("loadUserByUsername : " + username);

        Optional<Member> result = memberRepository.getWithRoles(username);

        if(result.isEmpty()){ //해당 아이디를 가진 사용자가 없다면
            throw new UsernameNotFoundException("username not found....");
        }

        Member member = result.get();

        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getMid(),
                        member.getMpw(),
                        member.getMemail(),
                        member.isMdel(),
                        false,
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                                .collect(Collectors.toList())
                );

        log.info("memberSecurityDTO");
        log.info(memberSecurityDTO);

        return memberSecurityDTO;

       /* UserDetails userDetails = User.builder()
                .username("user1")
                //.password("1111")
                //.password(passwordEncoder.encode("1111"))
                .authorities("ROLE_USER")
                .build();
                //[Username=user1, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true,
                //Granted Authorities=[ROLE_USER]], Credentials=[PROTECTED], Authenticated=true, Details=WebAuthenticationDetails [RemoteIpAddress=0:0:0:0:0:0:0:1,
                //SessionId=7169B6E2C4C890204477C1BFE622DBC9], Granted Authorities=[ROLE_USER]]]
        return userDetails;*/
    }


}
