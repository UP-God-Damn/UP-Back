package com.dsm.up.domain.post.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StateType {
    SOLUTION("해결"), QUESTION("질문");
    private final String status;
}
