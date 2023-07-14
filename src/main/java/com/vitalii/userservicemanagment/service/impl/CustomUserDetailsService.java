package com.vitalii.userservicemanagment.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.vitalii.userservicemanagment.model.UserAccount;
import com.vitalii.userservicemanagment.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountService userAccountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountService.findByUsername(username);
        User.UserBuilder userBuilder;
        userBuilder = withUsername(username);
        userBuilder.password(userAccount.getPassword());
        userBuilder.roles(userAccount.getRole().name());
        return userBuilder.build();
    }
}
