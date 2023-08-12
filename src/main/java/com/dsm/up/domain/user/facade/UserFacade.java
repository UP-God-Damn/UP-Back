package com.dsm.up.domain.user.facade;

import com.dsm.up.domain.user.presentation.dto.request.LogInRequest;
import com.dsm.up.domain.user.presentation.dto.response.TokenResponse;
import com.dsm.up.domain.user.service.LogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final LogInService logInService;

    public TokenResponse userLogIn(LogInRequest request) {
        return logInService.userLogIn(request);
    }
}
