package security;


import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@EnableGlobalMethodSecurity(prePostEnabled = true)  //사후 권한을 체크 할수있게 해준다.
// ex) controller 에서 @PreAuthorize("hasRole('USER')") 로 설정하면 user권한을 가진 사람만 접속할수 있다.
// 권한 메서드 종류 authenticated() - 인증된 사용자들만 허용 , permitAlld() - 모두 허용
// anonymous() - 익명의 사용자 권한 , hasRole(표현식) - 특정한 권한이 있는 사용자 허용 , hasAnyRole(표현식) - 여러권한중 하나만 존재해도 허용
public class CustomUserDetailsService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername : "+ username);

        UserDetails userDetails = User.builder()
                .username("user1")
               // .password("1111")
                .password(passwordEncoder.encode("1111")) //패스워드 인코딩 필요
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }
}
