package com.dsm.up.domain.user.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UserService {
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
                .nickname(user.getNickname())
                .accountId(user.getAccountId())
                .build();
    }
}
