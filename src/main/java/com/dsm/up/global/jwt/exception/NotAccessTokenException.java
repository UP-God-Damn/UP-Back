package com.dsm.up.global.jwt.exception;

import com.dsm.up.global.exception.BaseException;
import com.dsm.up.global.jwt.exception.error.TokenErrorCode;

public class NotAccessTokenException extends BaseException {
    public final static NotAccessTokenException EXCEPTION = new NotAccessTokenException();

    protected NotAccessTokenException() {
        super(TokenErrorCode.NOT_ACCESS_TOKEN);
    }
}
