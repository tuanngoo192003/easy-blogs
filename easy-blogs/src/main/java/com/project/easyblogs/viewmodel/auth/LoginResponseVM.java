package com.project.easyblogs.viewmodel.auth;

import com.project.core.enums.AccountStatus;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseVM{
    private String id;
    private String username;
    private String email;
    private List<String> roles;
    private AccountStatus status;
    private Boolean isVerified;
    private TokenResponseVM token;
}
