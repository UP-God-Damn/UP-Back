package com.dsm.up.domain.user.presentation;

import com.dsm.up.domain.user.presentation.dto.request.LoginRequest;
import com.dsm.up.domain.user.presentation.dto.request.SignUpRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.domain.user.presentation.dto.response.UserDetailResponse;
import com.dsm.up.domain.user.service.LoginService;
import com.dsm.up.domain.user.service.LogoutService;
import com.dsm.up.domain.user.service.SignUpService;
import com.dsm.up.domain.user.service.UserService;
import com.dsm.up.domain.user.service.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    private final LogoutService logoutService;
    private final UserService userService;
    private final UserUtil userUtil;

    @PostMapping(value = "/signup", consumes = {"application/json", "multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signUp(@RequestPart(value = "request") @Valid SignUpRequest request, @RequestPart(value = "image", required = false) MultipartFile file){
        return signUpService.userSignUp(request, file);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse login(@RequestBody LoginRequest request){
        return loginService.userLogIn(request);
    }

    @PutMapping("/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse reassignToken(@RequestHeader("Refresh-Token")String token) {
        return reassignToken(token);
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
    
}
