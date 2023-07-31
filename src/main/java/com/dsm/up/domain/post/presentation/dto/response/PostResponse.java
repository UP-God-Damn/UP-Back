package com.dsm.up.domain.post.presentation.dto.response;

import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostResponse {

    private final User user;
    private final String title;
    private final String content;
    private final String language;
    private final StateType state;
    private final MajorType major;
    private final LocalDate createDate;

    @Builder PostResponse(User user, String title, String content, String language, StateType state, MajorType major, LocalDate createDate) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.language = language;
        this.state = state;
        this.major = major;
        this.createDate = createDate;
    }
}
