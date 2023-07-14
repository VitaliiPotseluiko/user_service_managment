package com.vitalii.userservicemanagment.service.impl;

import com.vitalii.userservicemanagment.model.UserAccount;
import com.vitalii.userservicemanagment.repository.UserAccountRepository;
import com.vitalii.userservicemanagment.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccount create(UserAccount userAccount) {
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        if (userAccount.getRole() != UserAccount.Role.ADMIN) {
            userAccount.setRole(UserAccount.Role.USER);
        }
        userAccount.setStatus(UserAccount.Status.ACTIVE);
        userAccount.setCreatedUserDate(LocalDateTime.now());
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount getById(Long id) {
        return userAccountRepository.getReferenceById(id);
    }

    @Override
    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public boolean isExist(UserAccount userAccount) {
        return userAccountRepository.findByUsername(userAccount.getUsername()).isPresent();
    }
}
