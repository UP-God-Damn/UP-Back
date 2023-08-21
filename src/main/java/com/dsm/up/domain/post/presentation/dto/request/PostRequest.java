package com.dsm.up.domain.post.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class PostRequest {

    @NotBlank
    @Size(min = 5, max = 25)
    private String title;

    @NotBlank
    @Size(min = 20, max = 10000)
    private String content;

    @NotBlank
    private String language;

    @NotBlank
    private String state;

    @NotBlank
    private String major;
}
