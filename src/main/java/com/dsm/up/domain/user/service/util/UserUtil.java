package com.dsm.up.domain.user.service.util;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.UserNotFoundException;
import com.dsm.up.global.jwt.exception.TokenUnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserUtil {

	private final UserRepository userRepository;
	private final PostRepository postRepository;

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) throw TokenUnauthorizedException.EXCEPTION;
		return authentication.getName();
	}

	public User getUser() {
		return userRepository.findById(getUserId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}

	public Page<Post> getUserPostsPaged(Pageable pageable) {
		User user = getUser();
		return postRepository.findByAccountId(user.getAccountId(), pageable);
	}
}