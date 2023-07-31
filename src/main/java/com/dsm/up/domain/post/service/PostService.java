package com.dsm.up.domain.post.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.post.exception.NotFoundException;
import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long create(PostRequest request) {
        return postRepository.save(Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .language(request.getLanguage())
                .state(StateType.valueOf(request.getState()))
                .major(MajorType.valueOf(request.getMajor()))
                .build()).getId();
    }

    @Transactional // todo 수정하는 사람과 작성자가 일치하는 지 확인하기
    public Long update(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("존재히지 않는 게시글 입니다"));
        post.update(request.getTitle(), request.getContent(), request.getLanguage(), StateType.valueOf(request.getState()), MajorType.valueOf(request.getMajor()));
        return post.getId();
    }

}
