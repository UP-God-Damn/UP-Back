package com.dsm.up.domain.user.service;

import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.UserIdExistsException;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.global.aws.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Util s3Util;
    @Value("${cloud.aws.s3.default-image}")
    private String defaultImage;

    public void userSignUp(SignUpRequest request, MultipartFile file) {
        if(userRepository.existsByAccountId(request.getAccountId())) throw UserIdExistsException.EXCEPTION;

        User user = userRepository.save(User.builder()
            .accountId(request.getAccountId())
            .password(passwordEncoder.encode(request.getPassword()))
            .nickname(request.getNickname())
            .build());
        user.updatePath(file != null ? s3Util.upload(file) : defaultImage);
    }
}
