package com.dsm.up.domain.user.service;

import com.dsm.up.domain.user.domain.User;
import com.dsm.up.domain.user.domain.repository.UserRepository;
import com.dsm.up.domain.user.exception.UserIdExistsException;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void userSignUp(SignUpRequest request){
        if(userRepository.existsByAccountId(request.getAccountId())) throw UserIdExistsException.EXCEPTION;

        userRepository.save(User.builder()
            .accountId(request.getAccountId())
            .password(passwordEncoder.encode(request.getPassword()))
            .nickname(request.getNickname())
            .build());
    }
}
