package com.dsm.up.domain.user.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class LoginRequest {
    @NotBlank
    @Size(min = 6, max = 12)
    private String accountId;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
