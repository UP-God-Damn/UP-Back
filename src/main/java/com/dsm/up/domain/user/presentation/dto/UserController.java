package com.dsm.up.domain.user.presentation.dto;

import com.dsm.up.domain.user.presentation.dto.request.LoginRequest;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.domain.user.service.LoginService;
import com.dsm.up.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final SignUpService signUpService;
    private final LoginService loginService;

    @PostMapping(value = "/signup", consumes = {"application/json", "multipart/form-data"})
    public void signUp(@RequestPart(value = "request") @Valid SignUpRequest request, @RequestPart(value = "image", required = false) MultipartFile file){
        signUpService.userSignUp(request, file);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request){
        return loginService.userLogIn(request);
    }

    @PutMapping("/refresh")
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token")String token) {
        return reassignToken(token);
    }
}
