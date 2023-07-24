package com.dsm.up.domain.comment.domain;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 12)
    private Long userId;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false)
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(Long userId, Long postId, String content, LocalDate createDate){
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.createDate = LocalDate.now();
    }
}
