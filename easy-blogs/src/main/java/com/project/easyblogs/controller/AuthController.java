package com.project.easyblogs.controller;

import com.project.core.BaseController;
import com.project.core.config.security.JwtProvider;
import com.project.core.helper.MessageHelper;
import com.project.core.helper.VirtualThreadHelper;
import com.project.easyblogs.constant.Entity;
import com.project.easyblogs.entities.auth.Account;
import com.project.easyblogs.entities.auth.Token;
import com.project.easyblogs.services.auth.AccountService;
import com.project.easyblogs.services.auth.TokenService;
import com.project.easyblogs.viewmodel.auth.LoginRequestVM;
import com.project.easyblogs.viewmodel.auth.LoginResponseVM;
import com.project.easyblogs.viewmodel.auth.TokenResponseVM;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.project.core.viewmodel.ApiResponse;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AccountService accountService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("login")
    public ApiResponse<String> login(@RequestBody LoginRequestVM loginVM){
        Account account = accountService.findByFields(Map.of(Entity.Field.USERNAME, loginVM.identifier()));
        if(account==null){
            return new ApiResponse<>(false, MessageHelper.getMessage("validations.account.not_found"));
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginVM.identifier(), loginVM.password()));

            LoginResponseVM vm = new LoginResponseVM(account.getId(), account.getUsername(), account.getEmail(),
                    new ArrayList<>(),account.getStatus(), account.isVerified(), new TokenResponseVM(StringUtils.EMPTY, StringUtils.EMPTY));

            String accessToken;
            String refreshToken;
            if(account.isVerified()){
                accessToken = jwtProvider.createToken(account.getUsername());
                refreshToken = UUID.randomUUID().toString();

                VirtualThreadHelper.execute(() -> tokenService.save(Token.builder()
                        .username(authentication.getName())
                        .refreshToken(refreshToken)
                        .expiredAt(jwtProvider.getExpireTime())
                        .build()));

                vm.setToken(new TokenResponseVM(accessToken, refreshToken));
            }

            return new ApiResponse<>(true);
        } catch(Exception e){
            return new ApiResponse<>(false, MessageHelper.getMessage("validations.account.not_found"));
        }
    }
}
