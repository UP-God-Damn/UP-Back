package com.dsm.up.global.jwt;

import com.dsm.up.domain.user.domain.RefreshToken;
import com.dsm.up.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up.global.jwt.exception.TokenUnauthorizedException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Base64;
import java.util.Date;
//뭐지..?
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:application.yml")
public class JwtTokenProvider {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";

    @Value("${spring.jwt.key}")
    private String secretKey;

    @Value("${spring.jwt.access}")
    private Long accessTokenTime;

    @Value("${spring.jwt.refresh}")
    private Long refreshTokenTime;

    private final UserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenProvider(RefreshTokenRepository refreshTokenRepository, UserDetailsService userDetailsService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userDetailsService = userDetailsService;
    }

    public String generateAccessToken(String accountId) {
        return Jwts.builder()
                .setHeaderParam("typ", "Access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    public String generateRefreshToken(String accountId) {
        return Jwts.builder()
                .setHeaderParam("typ", "Refresh")
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTime * 1000))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("JWT 토큰이 만료되었습니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("잘못된 JWT 토큰입니다.");
        }
        return false;
    }

    public String getRefreshToken(String accountId) {
        return refreshTokenRepository.findByAccountId(accountId)
                .orElseThrow(() -> TokenUnauthorizedException.EXCEPTION)
                .getRefreshToken();
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private String subject(String token) {
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

}
