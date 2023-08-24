package com.dsm.up.global.config;

import com.dsm.up.global.exception.handler.AuthenticationEntryPointImpl;
import com.dsm.up.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .cors().and()
            .formLogin().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic().disable()
            .authorizeRequests()
            .antMatchers("/user/*").permitAll()
            .antMatchers(HttpMethod.GET, "/user").authenticated()
            .anyRequest().authenticated()
            .and()
            .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
            .and().apply(new FilterConfig(jwtTokenProvider))
            .and().build();
    }

}
