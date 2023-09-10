package com.dsm.up.domain.user.presentation;

import com.dsm.up.domain.user.exception.UserNotFoundException;
import com.dsm.up.domain.user.presentation.dto.request.LoginRequest;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.LoginService;
import com.dsm.up.domain.user.service.LogoutService;
import com.dsm.up.domain.user.service.SignUpService;
import com.dsm.up.domain.user.service.UserService;
import com.dsm.up.domain.user.service.util.UserUtil;
import com.dsm.up.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SignUpService signUpService;
    private final LoginService loginService;
    private final LogoutService logoutService;
    private final UserService userService;
    private final UserUtil userUtil;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signUp(@RequestBody SignUpRequest request) {
        return signUpService.userSignUp(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody LoginRequest request){
        return loginService.userLogIn(request);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token") String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw UserNotFoundException.EXCEPTION;
        }
        String accountId = jwtTokenProvider.getSubject(refreshToken);
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(accountId);
        } catch (UsernameNotFoundException e) {
            throw UserNotFoundException.EXCEPTION;
        }
        String newAccessToken = jwtTokenProvider.generateAccessToken(accountId);
        TokenResponse response = TokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();

        return response;
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout() {
        logoutService.logout();
    }

    @GetMapping
    public UserDetailResponse getUser() {
        return userService.getUser();
    }

    @GetMapping("/{accountId}")
    public void existsAccountId(@PathVariable String accountId) {
        userService.existsAccountId(accountId);
    }

    @PostMapping(value = "/profileImage/{accountId}", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public void profileImage(@PathVariable String accountId, @RequestPart(value = "image", required = false) MultipartFile file) {
        userUtil.upload(accountId, file);
    }
}