package com.dsm.up.global.jwt;

import com.dsm.up.global.jwt.auth.AuthDetailsService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Slf4j
@Component
@PropertySource("classpath:application.yml")
public class JwtTokenProvider {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer";
    private final AuthDetailsService authDetailsService;

    @Value("${spring.jwt.key}")
    private String secretKey;

    @Value("${spring.jwt.access}")
    private Long accessTokenTime;

    @Value("${spring.jwt.refresh}")
    private Long refreshTokenTime;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(AuthDetailsService authDetailsService, UserDetailsService userDetailsService) {
        this.authDetailsService = authDetailsService;
        this.userDetailsService = userDetailsService;
    }

    public String generateAccessToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setHeaderParam("typ", "Access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    public String generateRefreshToken() {
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

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(PREFIX))
            return bearerToken.replace(PREFIX, "");
        return null;
    }

}
