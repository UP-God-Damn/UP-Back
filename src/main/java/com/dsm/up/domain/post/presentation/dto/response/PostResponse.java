package com.dsm.up.domain.post.presentation.dto.response;


import java.util.List;

import com.dsm.up.domain.comment.domain.presentation.response.CommentResponse;
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
    private final List<CommentResponse> comments;
    private final String createDate;

    @Builder
    private PostResponse(String userNickname, String title, String content, String language, String  state, String major, List<CommentResponse> comments, String createDate) {
        this.userNickname = userNickname;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.major = major;
        this.comments = comments;
        this.createDate = createDate;
    }
}
