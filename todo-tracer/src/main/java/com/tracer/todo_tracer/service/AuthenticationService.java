package com.tracer.todo_tracer.service;

import com.tracer.todo_tracer.dto.AuthenticationDto;
import com.tracer.todo_tracer.dto.RegistrationDto;
import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public String registration(RegistrationDto regDto){

        UserEntity user = UserEntity
                .builder()
                .firstname(regDto.getFirstname())
                .lastname(regDto.getLastname())
                .email(regDto.getEmail())
                .password(passwordEncoder.encode(regDto.getPassword()))
                .build();

        user.setToken(jwtService.generateToken(user));

        userRepository.save(user);

        return "/v1/todo/person/auth";
    }

    public String authentication(AuthenticationDto authDto){

        manager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  authDto.getEmail(),
                  authDto.getPassword()
          )
        );

        UserEntity user = userRepository
                .findByEmail(authDto.getEmail())
                .orElseThrow();

        user.setToken(jwtService.generateToken(user));
        return "/v1/todo";
    }
}
