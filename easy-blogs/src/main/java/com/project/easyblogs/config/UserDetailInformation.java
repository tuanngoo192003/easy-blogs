package com.project.easyblogs.config;

import com.project.core.helper.StringListHelper;
import com.project.easyblogs.services.auth.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailInformation implements UserDetailsService {
    private final AccountService accountService;
    private final StringListHelper stringListHelper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final var userKey = String.format(CacheKey.USER_INFORMATION_FORMAT, username);
//        var user = accountCache.find(userKey);
//        if(user == null) {
//            user = accountService.findAccountByUsername(username);
//        }
        System.out.println(username);
        var user = accountService.findAccountByUsername(username);
        System.out.println("Tao o day");
        System.out.println("user: " + user);
        List<String> roleList = stringListHelper.convertToEntityAttribute(user.getRoles());
        final var roles = roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(roles)
                .build();
    }
}
