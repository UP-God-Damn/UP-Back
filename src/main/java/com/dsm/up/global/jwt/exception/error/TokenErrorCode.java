package com.dsm.up.global.jwt.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {

    NOT_ACCESS_TOKEN(401, "access 토큰이 아닙니다."),
    TOKEN_ERROR(401, "토큰이 잘못되지 않았는지 확인해주세요."),
    TOKEN_UNAUTHORIZED(401,"토큰이 유효 하지 않습니다.");

    private final int status;
    private final String message;
}
