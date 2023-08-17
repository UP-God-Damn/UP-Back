package com.dsm.up.global.aws.exception;

import com.dsm.up.global.aws.exception.error.ImageErrorCode;
import com.dsm.up.global.exception.BaseException;

public class ImageBadRequestException extends BaseException {
    public static final ImageBadRequestException EXCEPTION = new ImageBadRequestException();

    private ImageBadRequestException() {
        super(ImageErrorCode.IMAGE_BAD_REQUEST);
    }
}
