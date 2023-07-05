package com.vitalii.userservicemanagment.service.impl;

import com.vitalii.userservicemanagment.exception.AuthenticationException;
import com.vitalii.userservicemanagment.model.UserAccount;
import com.vitalii.userservicemanagment.repository.UserAccountRepository;
import com.vitalii.userservicemanagment.service.AuthenticationService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserAccountRepository userAccountRepository;

    public AuthenticationServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount login(String username, String password) throws AuthenticationException {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findByUsername(username);
        UserAccount userAccount;
        if (optionalUserAccount.isEmpty()) {
            throw new AuthenticationException("Your password or username incorrect!");
        }
        userAccount = optionalUserAccount.get();
        if (userAccount.getStatus() == UserAccount.Status.INACTIVE) {
                throw new AuthenticationException("User : " + userAccount.getUsername()
                        + " was blocked by Admin!");
        }
        if (!userAccount.getPassword().equals(password)) {
            throw new AuthenticationException("Your password or username incorrect!");
        }
        return userAccount;
    }
}
