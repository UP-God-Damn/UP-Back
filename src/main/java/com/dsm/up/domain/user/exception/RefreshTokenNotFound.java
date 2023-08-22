package com.dsm.up.domain.user.exception;

import com.dsm.up.domain.user.exception.error.UserErrorCode;
import com.dsm.up.global.exception.BaseException;

public class RefreshTokenNotFound extends BaseException {
    public static final RefreshTokenNotFound EXCEPTION = new RefreshTokenNotFound();

    public RefreshTokenNotFound() {super(UserErrorCode.REFRESH_TOKEN_NOT_FOUND);}
}
