package com.dsm.up.domain.post.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.domain.type.MajorType;
import com.dsm.up.domain.post.domain.type.StateType;
import com.dsm.up.domain.post.presentation.dto.response.PostListResponse;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostListService {

	private final PostRepository postRepository;
	private final UserUtil userUtil;

	public PostListResponse getUserPostsPaged(Pageable pageable) {
		Page<Post> posts = postRepository.findAllByUserOrderByCreateDateDesc(userUtil.getUser(), pageable);

		return new PostListResponse(posts.getTotalPages(),
			posts.stream().map(this::ofPostResponse).collect(Collectors.toList()));
	}

	public PostListResponse findPost(String title, String state, String major, Pageable page) {
		Page<Post> posts;

		if (state.isEmpty() && major.isEmpty())
			posts = postRepository.findAllByTitleContainingOrderByCreateDateDesc(title, page);
		else if (major.isEmpty())
			posts = postRepository.findAllByStateAndTitleContainingOrderByCreateDateDesc(StateType.valueOf(state), title, page);
		else if (state.isEmpty())
			posts = postRepository.findAllByTitleContainingAndMajorOrderByCreateDateDesc(title, MajorType.valueOf(major), page);
		else
			posts = postRepository.findAllByStateAndTitleContainingAndMajorOrderByCreateDateDesc(StateType.valueOf(state), title, MajorType.valueOf(major), page);


		return new PostListResponse(posts.getTotalPages(),
			posts.stream().map(this::ofPostResponse).collect(Collectors.toList()));
	}

	private PostListResponse.PostResponse ofPostResponse(Post post) {
		return PostListResponse.PostResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.userNickname(post.getUser().getNickname())
			.state(post.getState().getStatus())
			.major(post.getMajor().getMajor())
			.createDate(post.getCreateDate())
			.language(post.getLanguage())
			.build();
	}

}
