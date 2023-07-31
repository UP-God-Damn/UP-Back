package com.dsm.up.domain.post.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {

    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다.");

    private final int status;
    private final String message;
}
