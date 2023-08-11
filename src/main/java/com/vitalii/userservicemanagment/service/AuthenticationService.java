package com.vitalii.userservicemanagment.service;

import com.vitalii.userservicemanagment.exception.CustomAuthenticationException;
import com.vitalii.userservicemanagment.model.UserAccount;

public interface AuthenticationService {
    UserAccount login(String username, String password) throws CustomAuthenticationException;
}
