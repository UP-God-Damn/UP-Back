package com.dsm.up.domain.user.service;

import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.util.UserUtil;
import com.dsm.up.global.aws.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserUtil userUtil;
    private final S3Util s3Util;
    private final PostRepository postRepository;

    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
            .nickname(user.getNickname())
            .accountId(user.getAccountId())
            .profileImgeUrl(s3Util.getProfileImgeUrl(user.getPath()))
            .totalPosts(postRepository.countByUser(user))
            .build();
    }
}

