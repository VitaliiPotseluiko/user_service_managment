package com.vitalii.userservicemanagment.controller;

import com.vitalii.userservicemanagment.dto.request.UserAccountRequestDto;
import com.vitalii.userservicemanagment.dto.response.UserAccountResponseDto;
import com.vitalii.userservicemanagment.mapper.DtoMapper;
import com.vitalii.userservicemanagment.model.UserAccount;
import com.vitalii.userservicemanagment.service.UserAccountService;
import com.vitalii.userservicemanagment.validation.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserAccountController {
    private static final String MESSAGE_PATTERN = "%s \"%s\" is incorrect." +
            " It should consist of only latin letters and has size: from 1 to 16 symbols";
    private final UserAccountService userAccountService;
    private final Validator validator;
    private final DtoMapper<UserAccountRequestDto, UserAccount, UserAccountResponseDto> mapper;

    @GetMapping
    public String displayAllUsers(Model model) {
        List<UserAccountResponseDto> users = userAccountService.getAllUserAccounts()
                .stream()
                .map(mapper::toDto)
                .toList();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        List<UserAccountResponseDto> users = List.of(mapper.toDto(userAccountService.getById(id)));
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/new")
    public String addUser() {
        return "new";
    }

    @PostMapping("/add")
    public String createUser(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String username,
                             @RequestParam String password, Model model) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        List<String> messages = checkCorrectUserAccount(userAccount);
        if (!messages.isEmpty()) {
            model.addAttribute("messages", messages);
            return "/message";
        }
        userAccountService.create(userAccount);
        return "redirect:/user";
    }

    @GetMapping("/message")
    public String sendMessage() {
        return "message";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateUserPage(@PathVariable Long id, Model model) {
        UserAccount userAccount = userAccountService.getById(id);
        model.addAttribute("user", userAccount);
        return "/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, UserAccount updatedUserAccount, Model model) {
        UserAccount userAccount = userAccountService.getById(id);
        userAccount.setFirstName(updatedUserAccount.getFirstName());
        userAccount.setLastName(updatedUserAccount.getLastName());
        userAccount.setUsername(updatedUserAccount.getUsername());
        List<String> messages = checkCorrectUserAccount(userAccount);
        if (!messages.isEmpty()) {
            model.addAttribute("messages", messages);
            return "/message";
        }
        userAccountService.update(userAccount);
        return "redirect:/user";
    }

    @PostConstruct
    public void initAdmin() {
        UserAccount admin = new UserAccount();
        admin.setFirstName("Administrator");
        admin.setLastName("Main");
        admin.setUsername("Admin");
        admin.setPassword("123456789");
        admin.setStatus(UserAccount.Status.INACTIVE);
        admin.setRole(UserAccount.Role.ADMIN);
        admin.setCreatedUserDate(LocalDateTime.now());
        if (!userAccountService.isExist(admin)) {
            userAccountService.create(admin);
        }
    }

    private List<String> checkCorrectUserAccount(UserAccount userAccount) {
        List<String> messages = new ArrayList<>();
        if (!validator.isLatinLetter(userAccount.getFirstName())
                || !validator.isValidSize(userAccount.getFirstName())) {
            messages.add(String.format(MESSAGE_PATTERN, "First name", userAccount.getFirstName()));
        }

        if (!validator.isLatinLetter(userAccount.getLastName())
                || !validator.isValidSize(userAccount.getLastName())) {
            messages.add(String.format(MESSAGE_PATTERN, "Last name", userAccount.getLastName()));
        }

        if (!validator.isLatinLetter(userAccount.getUsername())
                || !validator.isValidSize(userAccount.getUsername())) {
            messages.add(String.format(MESSAGE_PATTERN, "User name", userAccount.getUsername()));
        }

        if (!validator.isPassword(userAccount.getPassword())) {
            messages.add("Incorrect password." +
                    " Password has to contain at least 1 digit, 1 spec symbol and 1 latin letter");
        }

        return messages;
    }
}
