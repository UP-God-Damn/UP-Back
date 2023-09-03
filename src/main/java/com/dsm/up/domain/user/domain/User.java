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
public class User {

    @Id
    @Column(nullable = false, length = 12, unique = true)
    private String accountId;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 12)
    private String nickname;

    private String path;

    @Builder
    public User(String accountId, String password, String nickname) {
        this.accountId = accountId;
        this.password = password;
        this.nickname = nickname;
    }

    public String updatePath(String path) {
        this.path = path;
        return this.path;
    }
}
