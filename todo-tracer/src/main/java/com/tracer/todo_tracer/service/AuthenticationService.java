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

    public void registration(RegistrationDto regDto){
        UserEntity user = UserEntity
                .builder()
                .firstname(regDto.getFirstname())
                .lastname(regDto.getLastname())
                .email(regDto.getEmail())
                .login(regDto.getLogin())
                .password(passwordEncoder.encode(regDto.getPassword()))
                .build();

        user.setToken(jwtService.generateToken(user));

        userRepository.save(user);
    }

    public UserEntity authentication(AuthenticationDto authDto){

        if(!authDto.getEmail().contains("@")){
            UserEntity userEntity = userRepository.findByLogin(authDto.getEmail()).orElseThrow();
            authDto.setEmail(userEntity.getUsername());
        }

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
        userRepository.save(user);

        return user;
    }

    public UserEntity getUserByLogin(String login){
        return userRepository
                .findByLogin(login)
                .orElseThrow();
    }
}
