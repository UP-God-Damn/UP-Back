package com.dsm.up.domain.user.service.util;

import com.dsm.up.global.aws.S3Util;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.UserNotFoundException;
import com.dsm.up.global.jwt.exception.TokenUnauthorizedException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UserUtil {

	private final UserRepository userRepository;
	private final S3Util s3Util;

	public String getUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) throw TokenUnauthorizedException.EXCEPTION;
		return authentication.getName();
	}

	public User getUser() {
		return userRepository.findById(getUserId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
	}

	public void upload(String accountId, MultipartFile file) {
		if (file != null && !file.isEmpty()) {
			User user = userRepository.findByAccountId(accountId)
					.orElseThrow(() -> UserNotFoundException.EXCEPTION);
			user.updatePath(s3Util.upload(file));
		}
	}

}