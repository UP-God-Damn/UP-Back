package com.dsm.up.domain.post.service;

import java.util.stream.Collectors;

import com.dsm.up.domain.comment.presentation.dto.response.CommentResponse;
import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.domain.user.exception.UserNotMatchException;
import com.dsm.up.domain.user.service.util.UserUtil;
import com.dsm.up.global.aws.S3Util;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dsm.up.domain.post.presentation.dto.response.PostListResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final S3Util s3Util;
    private final UserUtil userUtil;

    @Transactional
    public Long create(PostRequest request, MultipartFile file) {
        Post post = postRepository.save(Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .language(request.getLanguage())
                .state(StateType.valueOf(request.getState()))
                .major(MajorType.valueOf(request.getMajor()))
                .user(userUtil.getUser())
                .build());

        if(file != null) post.updatePath(s3Util.upload(file));

        return post.getId();
    }

    @Transactional
    public Long update(Long id, PostRequest request, MultipartFile file) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!post.getUser().getAccountId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;

        if (file != null) {
            if(post.getPath() != null) s3Util.delete(post.getPath());
            post.updatePath(s3Util.upload(file));
        }
        return post.update(request.getTitle(), request.getContent(), request.getLanguage(), StateType.valueOf(request.getState()), MajorType.valueOf(request.getMajor()));
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);
        if(!post.getUser().getAccountId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;
        if (post.getPath() != null) s3Util.delete(post.getPath());

        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostDetails(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

        return PostResponse.builder()
                .id(post.getId())
                .userNickname(post.getUser().getNickname())
                .profileImage(s3Util.getProfileImgeUrl(post.getUser().getPath()))
                .createDate(post.getCreateDate())
                .title(post.getTitle())
                .content(post.getContent())
                .file(s3Util.getPostImageUrl(post.getPath()))
                .language(post.getLanguage())
                .state(post.getState().getStatus())
                .major(post.getMajor().getMajor())
                .comments(post.getComments().stream().map(comment -> {
                            return CommentResponse.builder()
                                    .id(comment.getId())
                                    .createDate(comment.getCreateDate())
                                    .userNickname(comment.getUser().getNickname())
                                    .content(comment.getContent())
                                    .build();
                        }
                ).collect(Collectors.toList()))
                .build();
    }

    public PostListResponse findPost(String title, String state, String major) {
        List<Post> posts = new ArrayList<>();

        if (state.isEmpty() && major.isEmpty())
            posts = postRepository.findAllByTitleContainingOrderByCreateDateDesc(title);
        else if (major.isEmpty())
            posts = postRepository.findAllByStateAndTitleContainingOrderByCreateDateDesc(StateType.valueOf(state), title);
        else if (state.isEmpty())
            posts = postRepository.findAllByTitleContainingAndMajorOrderByCreateDateDesc(title, MajorType.valueOf(major));
        else
            posts = postRepository.findAllByStateAndTitleContainingAndMajorOrderByCreateDateDesc(StateType.valueOf(state), title, MajorType.valueOf(major));


        return new PostListResponse(posts.size(), posts.stream().map(post -> PostListResponse.PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .userNickname(post.getUser().getNickname())
                .state(post.getState().getStatus())
                .major(post.getMajor().getMajor())
                .createDate(post.getCreateDate())
                .build()).collect(Collectors.toList()));
    }

}
