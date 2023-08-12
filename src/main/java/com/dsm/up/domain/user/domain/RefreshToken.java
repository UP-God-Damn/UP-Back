package com.dsm.up.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String id;

    @Column(nullable = false)
    private String refreshToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
}

