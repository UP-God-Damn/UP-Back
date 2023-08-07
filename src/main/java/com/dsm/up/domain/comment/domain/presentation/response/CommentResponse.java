package com.dsm.up.domain.comment.domain.presentation.response;

import lombok.Builder;
import lombok.Getter;


@Getter
public class CommentResponse {

    private final String userNickname;
    private final String content;
    private final String createDate;

    @Builder
    public CommentResponse(String userNickname, String content, String createDate) {
        this.userNickname = userNickname;
        this.content = content;
        this.createDate = createDate;
    }

}
