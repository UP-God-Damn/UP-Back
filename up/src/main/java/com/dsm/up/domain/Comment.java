package com.dsm.up.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column(nullable = false)
    Long user_id;

    @Column(nullable = false)
    Long post_id;

    @Column(nullable = false, length = 5000)
    String content;

    @Column(nullable = false)
    Date create_date;

    @Builder
    public Comment(Long Id, Long user_id, Long post_id, String content, Date create_date){
        this.Id = Id;
        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
        this.create_date = create_date;
    }
}
