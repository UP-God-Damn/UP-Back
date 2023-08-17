package com.dsm.up.domain.user.exception;

import com.dsm.up.domain.user.exception.error.UserErrorCode;
import com.dsm.up.global.exception.BaseException;

public class UserNotMatchException extends BaseException {
    public static final UserNotMatchException EXCEPTION = new UserNotMatchException();

    public UserNotMatchException() {
        super(UserErrorCode.USER_NOT_MATCH_);
    }
}
