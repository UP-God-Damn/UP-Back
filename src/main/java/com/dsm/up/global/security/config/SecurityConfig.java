package com.dsm.up.global.security.config;

import com.dsm.up.global.security.Jwt.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signup").permitAll() //모두
                .antMatchers(HttpMethod.POST, "/login").permitAll() //모두
                .antMatchers(HttpMethod.PUT, "/reissue").authenticated()//인증된 사용자만 갱신 ㅇㅇ
                .anyRequest().authenticated()
                .and()
                .apply(new FilterConfig(jwtTokenProvider));
    }
}
