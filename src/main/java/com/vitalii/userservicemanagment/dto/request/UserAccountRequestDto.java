package com.vitalii.userservicemanagment.dto.request;

import com.vitalii.userservicemanagment.model.UserAccount;
import com.vitalii.userservicemanagment.validation.LatinLetter;
import com.vitalii.userservicemanagment.validation.Password;
import javax.validation.constraints.Size;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserAccountRequestDto {
    @Size(min = 1, max = 16)
    @LatinLetter
    private String firstName;
    @Size(min = 1, max = 16)
    @LatinLetter
    private String lastName;
    @Size(min = 3, max = 16)
    @LatinLetter
    private String username;
    @Password
    private String password;
    @Enumerated(EnumType.STRING)
    private UserAccount.Role role;
    @Enumerated(EnumType.STRING)
    private UserAccount.Status status;
    private LocalDateTime createdUserDate;
}
