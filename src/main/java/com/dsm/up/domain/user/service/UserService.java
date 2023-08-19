package com.dsm.up.domain.user.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.presentation.dto.response.PostListResponse;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.domain.post.service.util.PostUtil;
import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.presentation.dto.response.PostDetailResponse;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class UserService {
    private final UserUtil userUtil;
    private final PostRepository postRepository;
    private final PostUtil postUtil;
    public UserDetailResponse getUser() {
        User user = userUtil.getUser();

        return UserDetailResponse.builder()
                .nickname(user.getNickname())
                .accountId(user.getAccountId())
                .build();
    }

    public List<PostResponse> getUserPosts() {
        User user = userUtil.getUser();
        List<Post> userPosts = postRepository.findByAccountId(user.getAccountId());

        return userPosts.stream()
                .map(post -> PostResponse.builder()
                        .title(post.getTitle())
                        .createDate(post.getCreateDate())
                        .language(post.getLanguage())
                        .state(post.getState().getStatus())
                        .major(post.getMajor().getMajor())
                        .build())
                .collect(Collectors.toList());
    }

}

