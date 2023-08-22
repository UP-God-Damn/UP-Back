package com.dsm.up.domain.post.presentation.dto.response;


import java.util.List;

import com.dsm.up.domain.comment.presentation.dto.response.CommentResponse;
import lombok.Builder;
import lombok.Getter;


@Getter
public class PostResponse {

    private final Long id;
    private final String userNickname;
    private final String profileImage;
    private final String title;
    private final String content;
    private final String file;
    private final String language;
    private final String  state;
    private final String major;
    private final List<CommentResponse> comments;
    private final String createDate;

    @Builder
    private PostResponse(Long id, String userNickname, String profileImage, String title, String content, String file, String language, String  state, String major, List<CommentResponse> comments, String createDate) {
        this.id = id;
        this.userNickname = userNickname;
        this.profileImage = profileImage;
        this.title = title;
        this.content = content;
        this.file = file;
        this.language = language;
        this.state = state;
        this.major = major;
        this.comments = comments;
        this.createDate = createDate;
    }
}
