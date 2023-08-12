package com.dsm.up.domain.user.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    PASSWORD_MISS_MATCH(400, "비밀번호 불일치"),
    USER_NOT_FOUND(404, "유저를 찾을 수 없음");

    private final int status;
    private final String message;
}
