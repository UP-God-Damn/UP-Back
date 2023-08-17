package com.dsm.up.domain.user.exception;

import com.dsm.up.domain.user.exception.error.UserErrorCode;
import com.dsm.up.global.exception.BaseException;

public class UserIdExistsException extends BaseException {
    public static final UserIdExistsException EXCEPTION = new UserIdExistsException();

    public UserIdExistsException() { super(UserErrorCode.USER_ID_EXISTS); }
}