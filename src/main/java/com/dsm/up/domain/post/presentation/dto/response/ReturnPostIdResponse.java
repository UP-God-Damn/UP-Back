package com.dsm.up.domain.post.presentation.dto.response;

import lombok.Getter;

@Getter
public class ReturnPostIdResponse {

    private Long id;

    public ReturnPostIdResponse(Long id) {
        this.id = id;
    }

}
