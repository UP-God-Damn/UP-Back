package com.dsm.up.domain.user.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserUtil userUtil;
    private final PostRepository postRepository;
    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
                .nickname(user.getNickname())
                .accountId(user.getAccountId())
                .build();
    }

    public Page<PostResponse> getUserPostsPaged(Pageable pageable) {
        User user = userUtil.getUser();
        Page<Post> userPosts = userUtil.getUserPostsPaged(pageable);

        return userPosts.map(post -> PostResponse.builder()
                .title(post.getTitle())
                .createDate(post.getCreateDate())
                .language(post.getLanguage())
                .state(post.getState().getStatus())
                .major(post.getMajor().getMajor())
                .build());
    }
}

