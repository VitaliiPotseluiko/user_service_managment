package com.vitalii.userservicemanagment.dto.response;

import com.vitalii.userservicemanagment.model.UserAccount;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAccountResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private UserAccount.Role role;
    private UserAccount.Status status;
    private LocalDateTime createdUserDate;
}
