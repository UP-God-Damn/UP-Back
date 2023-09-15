package com.dsm.up.domain.comment.service;

import com.dsm.up.domain.comment.domain.Comment;
import com.dsm.up.domain.comment.domain.repository.CommentRepository;
import com.dsm.up.domain.comment.exception.CommentNotFoundException;
import com.dsm.up.domain.comment.presentation.dto.request.CommentRequest;
import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import com.dsm.up.domain.post.presentation.dto.response.ReturnIdResponse;
import com.dsm.up.domain.user.exception.UserNotMatchException;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public ReturnIdResponse creat(CommentRequest request) {
        Post post = postRepository.findById(request.getId())
                .orElseThrow(() -> PostNotFoundException.EXCEPTION);

        Comment comment = commentRepository.save(Comment.builder()
                .post(post)
                .user(userUtil.getUser())
                .content(request.getContent())
                .build());
        return new ReturnIdResponse(comment.getId());
    }

    @Transactional
    public Long update(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        if(!comment.getUser().getAccountId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;

        return comment.update(request.getContent());
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
        if(!comment.getUser().getAccountId().equals(userUtil.getUserId())) throw UserNotMatchException.EXCEPTION;

        commentRepository.delete(comment);
    }
}
