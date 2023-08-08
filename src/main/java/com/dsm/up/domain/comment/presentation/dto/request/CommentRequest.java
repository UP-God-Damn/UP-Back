package com.dsm.up.domain.comment.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CommentRequest {

    @NotBlank
    @Size(min = 5, max = 5000)
    private String content;
}
