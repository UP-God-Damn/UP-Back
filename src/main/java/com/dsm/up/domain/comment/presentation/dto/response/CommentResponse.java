package com.dsm.up.domain.comment.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;


@Getter
public class CommentResponse {

    private final Long id;
    private final String userNickname;
    private final String content;
    private final String createDate;

    @Builder
    public CommentResponse(Long id, String userNickname, String content, String createDate) {
        this.id = id;
        this.userNickname = userNickname;
        this.content = content;
        this.createDate = createDate;
    }

}
