package com.vitalii.userservicemanagment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitalii.userservicemanagment.exception.CustomAuthenticationException;
import com.vitalii.userservicemanagment.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class CustomFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;
    private final AuthenticationService authenticationService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            authenticationService.login(username, password);
        } catch (CustomAuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream()
                    .println(objectMapper.writeValueAsString(e.getMessage()));
        }
    }
}
