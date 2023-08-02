package com.dsm.up.domain.post.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return post.update(request.getTitle(), request.getContent(), request.getLanguage(), StateType.valueOf(request.getState()), MajorType.valueOf(request.getMajor()));
    }

    @Transactional //todo 삭제 요청하는 유저와 작성한 유저가 일치하는지 확인해야함
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostDetails(Long id) {
        Post post  = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);
        return PostResponse.builder()
                .userNickname(post.getUser().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .language(post.getLanguage())
                .state(post.getState().getStatus())
                .major(post.getMajor().getMajor())
                .comments(post.getComments().stream().map( comment -> {
                        return CommentResponse.builder()
                            .userNickname(comment.getUser().getNickname())
                            .content(comment.getContent())
                            .createDate(comment.getCreateDate())
                            .build();
                    }
                ).collect(Collectors.toList()))
                .build();
    }

}
