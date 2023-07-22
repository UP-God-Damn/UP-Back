package com.dsm.up.domain.comment.domain.repository;

import com.dsm.up.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
