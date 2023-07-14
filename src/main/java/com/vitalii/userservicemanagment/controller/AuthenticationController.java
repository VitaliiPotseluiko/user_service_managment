package com.vitalii.userservicemanagment.controller;

import com.vitalii.userservicemanagment.exception.AuthenticationException;
import com.vitalii.userservicemanagment.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password, Model model) {
        try {
            authenticationService.login(username, password);
        } catch (AuthenticationException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
        return "redirect:/user";
    }
}
