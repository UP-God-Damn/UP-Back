package com.dsm.up.domain.post.exception;

import com.dsm.up.domain.post.exception.error.PostErrorCode;
import com.dsm.up.global.exception.BaseException;

public class PostNotFoundException extends BaseException {
    public final static PostNotFoundException EXCEPTION = new PostNotFoundException();

    public PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}
