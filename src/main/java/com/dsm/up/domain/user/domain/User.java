package com.dsm.up.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12, unique = true)
    private String accountId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 12)
    private String nickname;


    @Builder
    public User(String accountId, String password, String nickname) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
    }

    public String getAccountId() {
        return accountId;
    }
}
