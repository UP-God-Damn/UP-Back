package com.dsm.up.global.jwt.exception;

import com.dsm.up.global.exception.BaseException;
import com.dsm.up.global.jwt.exception.error.TokenErrorCode;

public class TokenUnauthorizedException extends BaseException {
    public final static TokenUnauthorizedException EXCEPTION = new TokenUnauthorizedException();

    public TokenUnauthorizedException() {
        super(TokenErrorCode.TOKEN_UNAUTHORIZED);
    }
}
