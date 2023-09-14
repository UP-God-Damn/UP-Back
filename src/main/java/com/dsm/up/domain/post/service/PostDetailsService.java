package com.dsm.up.domain.post.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsm.up.domain.comment.presentation.dto.response.CommentResponse;
import com.dsm.up.domain.post.domain.Post;
import com.dsm.up.domain.post.domain.repository.PostRepository;
import com.dsm.up.domain.post.exception.PostNotFoundException;
import com.dsm.up.domain.post.presentation.dto.response.PostResponse;
import com.dsm.up.global.aws.S3Util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostDetailsService {

	private final PostRepository postRepository;
	private final S3Util s3Util;

	@Transactional(readOnly = true)
	public PostResponse getPostDetails(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> PostNotFoundException.EXCEPTION);

		return PostResponse.builder()
			.id(post.getId())
			.accountId(post.getUser().getAccountId())
			.userNickname(post.getUser().getNickname())
			.profileImage(s3Util.getProfileImageUrl(post.getUser().getPath()))
			.createDate(post.getCreateDate())
			.title(post.getTitle())
			.content(post.getContent())
			.file(s3Util.getPostImageUrl(post.getPath()))
			.language(post.getLanguage())
			.state(post.getState().getStatus())
			.major(post.getMajor().getMajor())
			.comments(post.getComments().stream().map(comment -> {
					return CommentResponse.builder()
						.id(comment.getId())
						.createDate(comment.getCreateDate())
						.userNickname(comment.getUser().getNickname())
							.profileImage(s3Util.getProfileImageUrl(comment.getUser().getPath()))
						.content(comment.getContent())
						.build();
				}
			).collect(Collectors.toList()))
			.build();
	}

}
