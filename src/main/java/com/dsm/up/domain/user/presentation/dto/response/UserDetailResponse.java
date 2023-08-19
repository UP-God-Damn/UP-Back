package com.dsm.up.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailResponse {
    private final String nickname;
    private final String accountId;
    private final String title;
}

