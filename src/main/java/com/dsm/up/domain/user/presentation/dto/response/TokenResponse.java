package com.dsm.up.domain.user.presentation.dto.response;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class TokenResponse {

    private final String accessToken;

    private final String refreshToken;

    @Builder
    private TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

