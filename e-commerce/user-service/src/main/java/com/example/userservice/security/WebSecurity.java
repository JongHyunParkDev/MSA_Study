package com.example.userservice.security;

import com.example.userservice.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;

    public WebSecurity(UserService userService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       Environment env) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }

    @Override
    // 접속 권한 관련된 override 된 메소드
    protected void configure(HttpSecurity http) throws Exception {
        // https://velog.io/@woohobi/Spring-security-csrf%EB%9E%80
        // csrf 사용 안함.
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        http.authorizeRequests().antMatchers("/**")
                .hasIpAddress("192.168.0.0") // 사용되고 있는 IP
                .and()
                .addFilter(getAuthenticationFilter());
    }

    @Override
    // 인증 관련된 override 된 메소드
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    private Filter getAuthenticationFilter() throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    // spring container 에서 BCryptPasswordEncoder를
    // 사용하는 service 에서 인식할 수 없음으로 bean 으로 등록해둔다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
