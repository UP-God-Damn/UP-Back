package com.dsm.up.domain.post.domain;

import com.dsm.up.domain.post.domain.type.State;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false, length = 70)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(nullable = false)
    private Date create_date;

    @Builder
    public Post(Long Id, Long user_id, String title, String content, String language, State state, Date create_date){
        this.Id = Id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.create_date = create_date;
    }
}
