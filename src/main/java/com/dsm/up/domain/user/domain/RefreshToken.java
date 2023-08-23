package com.dsm.up.domain.user.domain;

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
    private String accountId;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(String refreshToken, String accountId) {
        this.refreshToken = refreshToken;
        this.accountId = accountId;
    }

}
