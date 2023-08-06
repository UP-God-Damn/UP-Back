package com.dsm.up.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {
    private String nickname;
    private String accountId;
    private String password;
}
