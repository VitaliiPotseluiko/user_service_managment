package com.vitalii.userservicemanagment.service.impl;

import com.vitalii.userservicemanagment.exception.CustomAuthenticationException;
import com.vitalii.userservicemanagment.model.UserAccount;
import com.vitalii.userservicemanagment.repository.UserAccountRepository;
import com.vitalii.userservicemanagment.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount login(String username, String password) throws CustomAuthenticationException {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findByUsername(username);
        UserAccount userAccount;
        if (optionalUserAccount.isEmpty()) {
            throw new CustomAuthenticationException("Your password or username are incorrect!");
        }
        userAccount = optionalUserAccount.get();
        if (userAccount.getStatus() == UserAccount.Status.INACTIVE) {
                throw new CustomAuthenticationException("Sorry! User : " + userAccount.getUsername()
                        + " was blocked by Admin!");
        }
        if (!passwordEncoder.matches(password, userAccount.getPassword())) {
            throw new CustomAuthenticationException("Your password or username incorrect!");
        }
        return userAccount;
    }
}
