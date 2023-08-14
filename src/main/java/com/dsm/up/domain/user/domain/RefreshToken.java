package com.dsm.up.domain.user.domain;

import com.dsm.up.domain.user.domain.repository.RefreshTokenRepository;
import com.dsm.up.global.jwt.exception.TokenUnauthorizedException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String id;

    @Column(nullable = false)
    private String refreshToken;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Builder
    public RefreshToken(String id, String refreshToken, String accountId) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.accountId = accountId;
    }

    public String getAccountId() {
        return this.accountId;
    }

    public static String getRefreshToken(RefreshTokenRepository refreshTokenRepository, String accountId) {
        return refreshTokenRepository.findByAccountId(accountId)
                .orElseThrow(() -> TokenUnauthorizedException.EXCEPTION)
                .getRefreshToken();
    }
}
