package com.dsm.up.global.aws.exception.error;

import com.dsm.up.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageErrorCode implements ErrorCode {
    IMAGE_BAD_REQUEST(400, "이미지의 형식이 올바르지 않습니다"),
    IMAGE_NOT_UPLOAD(400, "이미지가 정상적으로 저장되지 않았습니다");

    private final int status;
    private final String message;
}
