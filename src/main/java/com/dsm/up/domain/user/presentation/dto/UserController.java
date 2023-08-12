package com.dsm.up.domain.user.presentation.dto;

import com.dsm.up.domain.user.presentation.dto.request.LogInRequest;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.domain.user.service.LogInService;
import com.dsm.up.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final SignUpService signUpService;
    private final LogInService logInService;

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid SignUpRequest request){
        signUpService.userSignUp(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LogInRequest request){
        return logInService.userLogIn(request);
    }

    @PutMapping("/refresh")
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token")String token) {
        return reassignToken(token);
    }
}
