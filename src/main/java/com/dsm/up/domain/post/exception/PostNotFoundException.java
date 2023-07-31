package com.dsm.up.domain.post.exception;

import com.dsm.up.domain.post.exception.error.PostErrorCode;
import com.dsm.up.global.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends BaseException {
    public final static PostNotFoundException EXCEPTION = new PostNotFoundException();

    public PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND);
    }
}
