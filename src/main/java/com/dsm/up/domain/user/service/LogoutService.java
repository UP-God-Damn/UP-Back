package com.dsm.up.domain.user.service;

import com.dsm.up.domain.user.domain.RefreshToken;
import com.dsm.up.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up.domain.user.exception.RefreshTokenNotFound;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class LogoutService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserUtil userUtil;

    public void logout() {
        RefreshToken refreshToken = refreshTokenRepository.findById(userUtil.getUserId())
                .orElseThrow(() -> RefreshTokenNotFound.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
