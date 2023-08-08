package com.dsm.up.domain.comment.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
