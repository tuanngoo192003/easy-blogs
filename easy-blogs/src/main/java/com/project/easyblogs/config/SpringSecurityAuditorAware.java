package com.project.easyblogs.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal;
        if (authentication == null || !authentication.isAuthenticated()) {
            principal = "SYSTEM";
        } else {
            Object principalObj = authentication.getPrincipal();
            if (principalObj instanceof UserDetails) {
                principal = ((UserDetails) principalObj).getUsername();
            } else {
                principal = principalObj.toString();
            }
        }
        log.info(String.format("Current auditor is >>> %s", principal));
        return Optional.ofNullable(principal);
    }
}
