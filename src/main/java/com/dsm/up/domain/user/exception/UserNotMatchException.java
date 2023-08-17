package com.dsm.up.domain.user.exception;

import com.dsm.up.global.exception.BaseException;
import com.dsm.up.domain.user.exception.error.UserErrorCode;

public class PasswordMissMatchException extends BaseException {
    public static final PasswordMissMatchException EXCEPTION = new PasswordMissMatchException();

    public PasswordMissMatchException() {
        super(UserErrorCode.PASSWORD_MISS_MATCH);
    }
}
