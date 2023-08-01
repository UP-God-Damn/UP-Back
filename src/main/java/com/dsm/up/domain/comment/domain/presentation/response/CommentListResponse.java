package com.dsm.up.domain.comment.domain.presentation.response;

import lombok.Builder;
import lombok.Getter;


@Getter
public class CommentListResponse {

    private String userNickname;
    private String content;
    private String createDate;

    @Builder
    public CommentListResponse(String userNickname, String content, String createDate) {
        this.userNickname = userNickname;
        this.content =content;
        this.createDate = createDate;
    }

}
