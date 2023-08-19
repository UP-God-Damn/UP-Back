package com.dsm.up.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDetailResponse {
    private final String title;
    private final String language;
    private final String state;
    private final String major;
    private final String createDate;
}
