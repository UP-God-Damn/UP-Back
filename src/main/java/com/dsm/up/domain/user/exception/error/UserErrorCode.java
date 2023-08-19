package com.dsm.up.domain.user.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    PASSWORD_MISS_MATCH(400, "비밀번호가 일치하지 않습니다."),
    USER_NOT_MATCH_(403,"작성자와 일치하지 않습니다"),
    USER_NOT_FOUND(404, "유저를 찾을 수 없습니다."),
    USER_ID_EXISTS(409, "유저 아이디가 이미 존재합니다."),
    REFRESH_TOKEN_NOT_FOUND(404, "리프레쉬 토큰을 찾을 수 없습니다. ");

    private final int status;
    private final String message;
}
