package com.vitalii.userservicemanagment.service;

import com.vitalii.userservicemanagment.model.UserAccount;
import java.util.List;

public interface UserAccountService {
    UserAccount create(UserAccount userAccount);

    UserAccount update(UserAccount userAccount);

    List<UserAccount> getAllUserAccounts();

    UserAccount getById(Long id);
    UserAccount findByUsername(String username);

    boolean isExist(UserAccount userAccount);
}
