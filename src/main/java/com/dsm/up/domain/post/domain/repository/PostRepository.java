package com.dsm.up.domain.post.domain.repository;

import com.dsm.up.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
