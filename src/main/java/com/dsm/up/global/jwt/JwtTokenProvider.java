package com.dsm.up.global.jwt;

import com.dsm.up.global.jwt.exception.NotAccessTokenException;
import com.dsm.up.global.jwt.exception.TokenErrorException;
import com.dsm.up.global.jwt.exception.TokenUnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:application.yml")
public class JwtTokenProvider {

    @Value("${spring.jwt.access}")
    private Long accessTokenTime;

    @Value("${spring.jwt.refresh}")
    private Long refreshTokenTime;

    private final UserDetailsService userDetailsService;
    private final Key key;

    public JwtTokenProvider(@Value("${spring.jwt.key}") String secretKey, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateAccessToken(String accountId) {
        return Jwts.builder()
                .setHeaderParam("typ", "access")
                .setSubject(accountId)
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .compact();
    }

    public String generateRefreshToken(String accountId) {
        return Jwts.builder()
                .setHeaderParam("typ", "refresh")
                .setSubject(accountId)
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTime * 1000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        isRefreshToken(token);

        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw TokenErrorException.EXCEPTION;
        } catch (ExpiredJwtException e) {
            log.info("JWT 토큰이 만료되었습니다.");
            throw TokenUnauthorizedException.EXCEPTION;
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT 토큰입니다.");
            throw TokenErrorException.EXCEPTION;
        } catch (IllegalArgumentException e) {
            log.info("잘못된 JWT 토큰입니다.");
            throw TokenErrorException.EXCEPTION;
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    private void isRefreshToken(String token) {
        if(!getHeader(token).equals("refresh")) throw NotAccessTokenException.EXCEPTION;
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

    private String getSubject(String token) {
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getHeader();
    }

}