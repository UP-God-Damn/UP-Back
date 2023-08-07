package com.dsm.up.domain.post.domain.repository;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAllByStateAndTitleContainingAndMajorOrderByCreateDateDesc(StateType state, String title, MajorType major);
    List<Post> findAllByStateAndTitleContainingOrderByCreateDateDesc(StateType state, String title);
    List<Post> findAllByTitleContainingAndMajorOrderByCreateDateDesc(String title, MajorType major);
    List<Post> findAllByTitleContainingOrderByCreateDateDesc(String title);
}
