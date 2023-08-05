package com.dsm.up.domain.post.service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import com.dsm.up.domain.post.presentation.dto.request.PostRequest;
import com.dsm.up.domain.post.presentation.dto.response.PostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostListResponse findPost(String title, String state, String major) {
        List<Post> posts = new ArrayList<>();

        if (state.equals("SOLUTION")) {
            if (title.isEmpty()) {
                if (major.isEmpty()) posts = postRepository.findAllByStateOrderByCreateDateDesc(StateType.SOLUTION);
                else
                    posts = postRepository.findAllByStateAndMajorOrderByCreateDateDesc(StateType.SOLUTION, MajorType.valueOf(major));
            } else {
                if (major.isEmpty())
                    posts = postRepository.findAllByStateAndTitleContainingOrderByCreateDateDesc(StateType.SOLUTION, title);
                else
                    posts = postRepository.findAllByStateAndTitleContainingAndMajorOrderByCreateDateDesc(StateType.SOLUTION, title, MajorType.valueOf(major));
            }
        } else if (state.equals("QUESTION")) {
            if (title.isEmpty()) {
                if (major.isEmpty()) posts = postRepository.findAllByStateOrderByCreateDateDesc(StateType.QUESTION);
                else
                    posts = postRepository.findAllByStateAndMajorOrderByCreateDateDesc(StateType.QUESTION, MajorType.valueOf(major));
            } else {
                if (major.isEmpty())
                    posts = postRepository.findAllByStateAndTitleContainingOrderByCreateDateDesc(StateType.QUESTION, title);
                else
                    posts = postRepository.findAllByStateAndTitleContainingAndMajorOrderByCreateDateDesc(StateType.QUESTION, title, MajorType.valueOf(major));
            }
        } else {
            if (title.isEmpty()) {
                if (major.isEmpty()) posts = postRepository.findAllByOrderByCreateDateDesc();
                else posts = postRepository.findAllByMajorOrderByCreateDateDesc(MajorType.valueOf(major));
            } else {
                if (major.isEmpty()) posts = postRepository.findAllByTitleContainingOrderByCreateDateDesc(title);
                else
                    posts = postRepository.findAllByTitleContainingAndMajorOrderByCreateDateDesc(title, MajorType.valueOf(major));
            }

        }

        return new PostListResponse(posts.stream().map(post -> PostListResponse.PostResponse.builder()
                .title(post.getTitle())
                .userNickname(post.getUser().getNickname())
                .state(post.getState().getStatus())
                .major(post.getMajor().getMajor())
                .createDate(post.getCreateDate())
                .build()).collect(Collectors.toList()), posts.size());
    }

}
