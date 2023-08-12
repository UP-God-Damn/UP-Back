package com.dsm.up.domain.user.service;

import com.dsm.up.domain.user.domain.RefreshToken;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.PasswordMissMatchException;
import com.dsm.up.domain.user.exception.UserNotFoundException;
import com.dsm.up.domain.user.presentation.dto.request.LogInRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse userLogIn(LogInRequest request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordMissMatchException().EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshTokenValue = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        RefreshToken refreshToken = RefreshToken.builder()
                .id(user.getAccountId())
                .refreshToken(refreshTokenValue)
                .accountId(user.getAccountId())
                .build();

        refreshTokenRepository.save(refreshToken);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenValue)
                .build();
    }
}
