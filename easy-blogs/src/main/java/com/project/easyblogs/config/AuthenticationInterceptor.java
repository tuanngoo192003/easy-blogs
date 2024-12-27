package com.project.easyblogs.config;

import com.project.core.config.security.JwtProvider;
import com.project.core.enums.SystemUserCases;
import com.project.easyblogs.services.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private final AuthenticationService authenticationService;


    public AuthenticationInterceptor(JwtProvider jwtProvider,
                                     AuthenticationService authenticationService) {
        this.jwtProvider = jwtProvider;
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Authentication authentication = handlerMethod.getMethodAnnotation(Authentication.class);

        String token = jwtProvider.resolveToken(request);

        if(authentication.value().equals(SystemUserCases.DEFAULT)){
            token = jwtProvider.resolveToken(request);
            if (Objects.isNull(token)) {
                return true;
            }
        }

        if (Objects.isNull(token)) {
            throw new AccessDeniedException("ACCESS_DENIED");
        }

        try {
            jwtProvider.validateToken(token);
            org.springframework.security.core.Authentication auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

            if (userHasRequiredRole(authentication, auth)) {
                return true;
            }

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } catch (Exception ex) {
            handleException(ex, request, response);
            return false;
        }
    }

    private boolean userHasRequiredRole(Authentication authentication, org.springframework.security.core.Authentication auth) {
        if(authentication.value().equals(SystemUserCases.DEFAULT)) {
            return true;
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        com.project.easyblogs.entities.auth.Authentication authen = authenticationService.findByUsernameAndUserCase(authentication.value().name(),
                userDetails.getUsername());

        if(Objects.isNull(authen)) {
            return false;
        }
        List<String> roles = authen.getRoles();

        return roles.stream().anyMatch(role -> userDetails.getAuthorities().stream().anyMatch(
                grantedAuthority -> Objects.equals(grantedAuthority.getAuthority(), role)));
    }

    private void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        SecurityContextHolder.clearContext();
        String jsonMessage = """
                {
                    "status": false,
                    "message": "%s",
                    "path": "%s"
                }
                """.formatted(ex.getMessage(), request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(jsonMessage);
    }
}
