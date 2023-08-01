package com.dsm.up.domain.post.presentation.dto.response;


import com.dsm.up.domain.comment.domain.presentation.response.CommentListResponse;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostResponse {

    private final String userNickname;
    private final String title;
    private final String content;
    private final String language;
    private final String  state;
    private final String major;
    private final CommentListResponse comment;
    private final String createDate;

    @Builder
    PostResponse(String userNickname, String title, String content, String language, String  state, String major, CommentListResponse comment, String createDate) {

        this.userNickname = userNickname;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.major = major;
        this.comment = comment;
        this.createDate = createDate;
    }
}
