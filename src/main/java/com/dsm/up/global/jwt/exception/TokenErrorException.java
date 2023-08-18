package com.dsm.up.global.jwt.exception;

import com.dsm.up.global.exception.BaseException;
import com.dsm.up.global.jwt.exception.error.TokenErrorCode;

public class TokenErrorException extends BaseException {
    public final static TokenErrorException EXCEPTION = new TokenErrorException();

    protected TokenErrorException() {
        super(TokenErrorCode.TOKEN_ERROR);
    }
}
