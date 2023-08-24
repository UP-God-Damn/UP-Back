package com.dsm.up.domain.user.service;

import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.UserIdExistsException;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.util.UserUtil;
import com.dsm.up.global.aws.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserUtil userUtil;
    private final S3Util s3Util;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
            .nickname(user.getNickname())
            .accountId(user.getAccountId())
            .profileImgeUrl(s3Util.getProfileImageUrl(user.getPath()))
            .totalPosts(postRepository.countByUser(user))
            .build();
    }

    public void existsAccountId(String accountId) {
        if(userRepository.existsByAccountId(accountId)) throw UserIdExistsException.EXCEPTION;
    }

}

