package com.dsm.up.domain.post.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.user.exception.UserNotMatchException;
import com.dsm.up.domain.user.service.util.UserUtil;
import com.dsm.up.global.aws.S3Util;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

}
