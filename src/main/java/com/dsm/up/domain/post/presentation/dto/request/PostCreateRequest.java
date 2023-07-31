package com.dsm.up.domain.post.presentation.dto.request;

import com.dsm.up.domain.post.domain.type.StateType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class PostCreateRequest {

    @NotBlank
    @Size(min = 5, max = 70)
    private String title;

    @NotBlank
    @Size(min = 20, max = 10000)
    private String content;

    @NotBlank
    private String language;

    @NotBlank
    private StateType state;
}
