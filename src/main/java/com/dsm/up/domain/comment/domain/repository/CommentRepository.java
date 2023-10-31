package com.dsm.up.domain.comment.domain.repository;

import com.dsm.up.domain.comment.domain.Comment;
import com.dsm.up.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findAllByPostOrderByIdDesc(Post post);
    
}
