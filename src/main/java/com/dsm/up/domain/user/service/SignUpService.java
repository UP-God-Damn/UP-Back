package com.dsm.up.domain.user.service;

import com.dsm.up.domain.user.domain.RefreshToken;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.UserIdExistsException;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponse userSignUp(SignUpRequest request) {
        if (userRepository.existsByAccountId(request.getAccountId())) {
            throw new UserIdExistsException();
        }

        User user = userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build());

        return TokenResponse.builder()
            .accessToken(jwtTokenProvider.generateAccessToken(user.getAccountId()))
            .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                .accountId(user.getAccountId())
                .refreshToken(jwtTokenProvider.generateRefreshToken(user.getAccountId()))
                .build()).getRefreshToken())
            .build();
    }

}

