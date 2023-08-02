package com.dsm.up.domain.post.domain.repository;

import com.dsm.up.domain.post.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByTitleAndStateAndLanguageAndMajor(String title, String State, String Language, String major);
}
