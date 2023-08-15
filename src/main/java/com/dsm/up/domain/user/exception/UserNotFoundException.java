package com.dsm.up.domain.user.exception;

import com.dsm.up.global.exception.BaseException;
import com.dsm.up.domain.user.exception.error.UserErrorCode;

public class UserNotFoundException extends BaseException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    public UserNotFoundException() { super(UserErrorCode.USER_NOT_FOUND); }
}