package com.example.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // https://velog.io/@woohobi/Spring-security-csrf%EB%9E%80
        // csrf 사용 안함.
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/users/**").permitAll();

    }

    // spring container 에서 BCryptPasswordEncoder를
    // 사용하는 service 에서 인식할 수 없음으로 bean 으로 등록해둔다.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
