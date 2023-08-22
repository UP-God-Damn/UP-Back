package com.dsm.up.domain.post.domain.repository;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.user.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    Page<Post> findAllByTitleContainingOrderByCreateDateDesc(String title, Pageable pageable);
    Page<Post> findAllByStateAndTitleContainingOrderByCreateDateDesc(StateType state, String title, Pageable pageable);
    Page<Post> findAllByTitleContainingAndMajorOrderByCreateDateDesc(String title, MajorType major, Pageable pageable);
    Page<Post> findAllByStateAndTitleContainingAndMajorOrderByCreateDateDesc(StateType state, String title, MajorType major, Pageable pageable);
    Page<Post> findAllByUserOrderByCreateDateDesc(User user, Pageable pageable);
    long countByUser(User user);
}
