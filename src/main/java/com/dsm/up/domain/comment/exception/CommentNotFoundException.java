package com.dsm.up.domain.comment.exception;

import com.dsm.up.domain.comment.exception.error.CommentErrorCode;
import com.dsm.up.global.exception.BaseException;

public class CommentNotFoundException extends BaseException {

    public final static CommentNotFoundException EXCEPTION = new  CommentNotFoundException();

    public CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
