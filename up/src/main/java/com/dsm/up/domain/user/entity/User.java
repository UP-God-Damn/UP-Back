package com.dsm.up.domain.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 12, unique = true)
    private String user_id;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 12)
    private String nickname;

    @Column(nullable = false, length = 10)
    private String major;

    @Builder
    public User(Long Id, String user_id, String password, String nickname, String major) {
        this.Id = Id;
        this.user_id = user_id;
        this.password = password;
        this.nickname = nickname;
        this.major = major;
    }
}
