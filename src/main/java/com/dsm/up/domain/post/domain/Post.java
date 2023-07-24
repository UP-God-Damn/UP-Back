package com.dsm.up.domain.post.domain;

import com.dsm.up.domain.comment.domain.Comment;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 70)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StateType state;

    @Column(nullable = false)
    private LocalDate createDate;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Post(Long userId, String title, String content, String language, StateType state, Date creatDate){
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.createDate = LocalDate.now();
    }
}
