package com.dsm.up.global.jwt.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {

    TOKEN_UNAUTHORIZED(401,"토큰이 유효 하지 않습니다.");

    private final int status;
    private final String message;
}
