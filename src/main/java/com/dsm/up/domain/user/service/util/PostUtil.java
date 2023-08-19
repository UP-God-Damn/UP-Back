package com.dsm.up.domain.post.service.util;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUtil {

    private final PostRepository postRepository;

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->  PostNotFoundException.EXCEPTION);
    }
}

