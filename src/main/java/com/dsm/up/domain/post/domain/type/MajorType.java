package com.dsm.up.domain.post.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MajorType {
    BACKEND("백엔드"), FRONTEND("프론트엔드"), ANDROID("안드로이드"), FLUTTER("플러터"), IOS("ios"), DEVOPS("데브옵스"), ETC("기타");

    private final String major;
}
