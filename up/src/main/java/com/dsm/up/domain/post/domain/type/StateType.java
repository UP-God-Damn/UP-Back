package com.dsm.up.domain.post.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StateType {
    RESOLVED("해결"), UNRESOLVED("미해결");
    private final String status;
}
