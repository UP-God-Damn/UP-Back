package com.dsm.up.global.aws.exception;

import com.dsm.up.global.aws.exception.error.ImageErrorCode;
import com.dsm.up.global.exception.BaseException;

public class ImageNotUploadException extends BaseException {
    public static final ImageNotUploadException EXCEPTION = new ImageNotUploadException();

    private ImageNotUploadException() {
        super(ImageErrorCode.IMAGE_NOT_UPLOAD);
    }
}
