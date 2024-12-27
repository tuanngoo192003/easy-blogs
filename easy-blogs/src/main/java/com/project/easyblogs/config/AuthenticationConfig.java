package com.project.easyblogs.config;

import com.project.core.config.security.JwtProvider;
import com.project.easyblogs.services.auth.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationConfig implements WebMvcConfigurer {
    private final JwtProvider jwtProvider;
    private final AuthenticationService authenticationService;

    public AuthenticationConfig(JwtProvider jwtProvider, AuthenticationService authenticationService) {
        this.jwtProvider = jwtProvider;
        this.authenticationService = authenticationService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(jwtProvider, authenticationService));
    }
}
