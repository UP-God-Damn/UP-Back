package com.dsm.up.domain.post.presentation.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostResponse {

    private final String user;
    private final String title;
    private final String content;
    private final String language;
    private final String  state;
    private final String major;
    private final String comment;
    private final LocalDate createDate;

    @Builder
    PostResponse(String user, String title, String content, String language, String  state, String major, String comment, LocalDate createDate) {

        this.user = user;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.major = major;
        this.comment = comment;
        this.createDate = createDate;
    }
}
