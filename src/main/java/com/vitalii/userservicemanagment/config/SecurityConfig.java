package com.vitalii.userservicemanagment.config;

import com.vitalii.userservicemanagment.provider.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //private final PasswordEncoder passwordEncoder;
    //private final UserDetailsService userDetailsService;
    private final AuthenticationProvider customAuthenticationProvider;
    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/login", "/register")
                                        .permitAll()
                                        .requestMatchers(HttpMethod.GET,
                                                "/user",
                                                "/user/{id}")
                                        .hasAnyRole("USER", "ADMIN")
                                        .requestMatchers(HttpMethod.POST,
                                                "/user/new/add",
                                                "/user/update/{id}",
                                                "/user/block/{id}")
                                        .hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET,
                                                "/user/{id}/edit",
                                                "/user/new/add")
                                        .hasRole("ADMIN")
                                        .anyRequest()
                                        .authenticated())
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/login")
                //.defaultSuccessUrl("/user", true)
                .permitAll()
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return customAuthenticationManager;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
