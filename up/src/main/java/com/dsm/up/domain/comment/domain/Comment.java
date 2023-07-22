package com.dsm.up.domain.comment.domain;

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

    @Builder
    public Comment(Long userId, Long postId, String content, LocalDate createDate){
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.createDate = LocalDate.now();
    }
}
