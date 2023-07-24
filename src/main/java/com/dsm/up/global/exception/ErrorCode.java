package com.dsm.up.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    PASSWORD_MISS_MATCH(400, "Password Miss Match"),

    JWT_EXPIRED(401, "Jwt Expired"),

    USER_NOT_FOUND(404, "User Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),
    COMMENT_NOT_FOUND(404, "Comment Not Found"),

    ACCOUNTID_ALREADY_EXISTS(409, "AccountID Already Exists"),
    NICKNAME_ALREADY_EXISTS(409, "Nickname Already Exists"),

    SERVER_ERROR(500, "Server Error");

    private final int status;
    private final String message;
}
