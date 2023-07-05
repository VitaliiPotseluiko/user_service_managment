package com.vitalii.userservicemanagment.mapper;

import com.vitalii.userservicemanagment.dto.request.UserAccountRequestDto;
import com.vitalii.userservicemanagment.dto.response.UserAccountResponseDto;
import com.vitalii.userservicemanagment.model.UserAccount;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDtoMapper
        implements DtoMapper<UserAccountRequestDto, UserAccount, UserAccountResponseDto> {
    @Override
    public UserAccount toModel(UserAccountRequestDto requestDto) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(requestDto.getFirstName());
        userAccount.setLastName(requestDto.getLastName());
        userAccount.setUsername(requestDto.getUsername());
        userAccount.setRole(requestDto.getRole());
        userAccount.setStatus(requestDto.getStatus());
        userAccount.setCreatedUserDate(requestDto.getCreatedUserDate());
        return userAccount;
    }

    @Override
    public UserAccountResponseDto toDto(UserAccount model) {
        UserAccountResponseDto userAccountResponseDto = new UserAccountResponseDto();
        userAccountResponseDto.setId(model.getId());
        userAccountResponseDto.setFirstName(model.getFirstName());
        userAccountResponseDto.setLastName(model.getLastName());
        userAccountResponseDto.setUsername(model.getUsername());
        userAccountResponseDto.setRole(model.getRole());
        userAccountResponseDto.setStatus(model.getStatus());
        userAccountResponseDto.setCreatedUserDate(model.getCreatedUserDate());
        return userAccountResponseDto;
    }
}
