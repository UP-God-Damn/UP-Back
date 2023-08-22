package com.dsm.up.domain.user.presentation.dto.response;

import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class UserDetailResponse {

    private final String nickname;
    private final String accountId;
    private final String profileImgeUrl;
    private final long totalPosts;

}

