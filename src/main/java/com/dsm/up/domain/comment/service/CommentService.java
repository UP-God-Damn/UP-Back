package com.dsm.up.domain.comment.service;

import com.dsm.up.domain.comment.domain.Comment;
import com.dsm.up.domain.comment.domain.repository.CommentRepository;
import com.dsm.up.domain.comment.exception.CommentNotFoundException;
import com.dsm.up.domain.comment.presentation.dto.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Long creat(CommentRequest request) {
        return commentRepository.save(Comment.builder()
                .content(request.getContent())
                //.user()
                .build()).getId();
    }

    @Transactional //comment 작성자와 일치하는지 확인
    public Long update(Long id, CommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        return comment.update(request.getContent());
    }

    @Transactional //comment 작성자와 일치하는지 확인
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        commentRepository.delete(comment);
    }
}
