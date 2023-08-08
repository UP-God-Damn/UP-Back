package com.dsm.up.domain.comment.domain.repository;

import com.dsm.up.domain.comment.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    
}
