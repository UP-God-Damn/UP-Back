package com.dsm.up.domain.user.presentation.dto;

import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final SignUpService signUpService;

    @PostMapping("/user/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request){
        signUpService.userSignUp(request);
    }

}
