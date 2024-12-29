package com.project.easyblogs.config;

import com.project.core.config.security.JwtProvider;
import com.project.core.helper.StringListHelper;
import com.project.easyblogs.services.auth.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationConfig implements WebMvcConfigurer {
    private final JwtProvider jwtProvider;
    private final AuthenticationService authenticationService;
    private final StringListHelper stringListHelper;

    public AuthenticationConfig(JwtProvider jwtProvider, AuthenticationService authenticationService,
                                StringListHelper stringListHelper) {
        this.jwtProvider = jwtProvider;
        this.authenticationService = authenticationService;
        this.stringListHelper = stringListHelper;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(jwtProvider, authenticationService, stringListHelper));
    }
}
