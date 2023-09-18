package com.dsm.up.domain.post.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MajorType {
    BACKEND("백엔드"), FRONTEND("프론트엔드"), FLUTTER("플러터"), AOS("안드로이드"), IOS("ios"), DEVOPS("devops"), EMBEDDED("임베디드"), SECURITY("정보보안"), ETC("기타");

    private final String major;
}
