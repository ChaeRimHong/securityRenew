package com.example.securityRenew.persistence.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.securityRenew.persistence.enums.UserAuthority;
import com.example.securityRenew.persistence.enums.UserRole;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /* @formatter:off */
        http

                .authorizeRequests()
                .antMatchers("/", "/home", "/signUp","/board","/board_detail","/board_detail_do","/search","/search_do","/hello_board","/hello_board_out").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
                .antMatchers("/system","user_list").hasRole(UserRole.SYSTEM.toString()) // SYSTEM 역할을 가지고 있어야 접근 허용
                .antMatchers("/system/create").access("hasRole('" +  UserRole.SYSTEM.toString() +  "') and hasAuthority('" + UserAuthority.OP_CREATE_DATA.toString() + "')") // SYSTEM 역할과 OP_CREATE_DATA 권한을 가지고 있어야 접근 허용
                .antMatchers("/system/delete").access("hasRole('" +  UserRole.SYSTEM.toString() +  "') and hasAuthority('" + UserAuthority.OP_DELETE_DATA.toString() + "')") // SYSTEM 역할과 OP_DELETE_DATA 권한을 가지고 있어야 접근 허용
                .anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
                .and()
                //'스프링 부트 Request method 'POST' not supported' 이거 해결할려고 내가 넣음
                //https://chamch-dev.tistory.com/30
                .csrf()
                .ignoringAntMatchers("/board_in")
                .ignoringAntMatchers("/board_in_save")
                .ignoringAntMatchers("/board")
                .ignoringAntMatchers("/search")
                .ignoringAntMatchers("/search_do")
                .ignoringAntMatchers("/board_update")
                .ignoringAntMatchers("/board_update_save")
                .ignoringAntMatchers("/hello_board_in")
                .ignoringAntMatchers("/mypage")
                .ignoringAntMatchers("/mypage_save")
                //
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login") // 기본 로그인 페이지
                .and()
                .logout()
                .permitAll()
                // .logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
                // .logoutSuccessUrl("/login?logout") // 로그아웃 성공 URL (기본 값 : "/login?logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
                .deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
                .invalidateHttpSession(true) // 로그아웃 시 세션 종료
                .clearAuthentication(true) // 로그아웃 시 권한 제거
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied");

        return http.build();
        /* @formatter:on */
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}