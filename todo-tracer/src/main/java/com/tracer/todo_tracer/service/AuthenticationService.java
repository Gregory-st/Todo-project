package com.tracer.todo_tracer.service;

import com.tracer.todo_tracer.dto.AuthenticationDto;
import com.tracer.todo_tracer.dto.PasswordDto;
import com.tracer.todo_tracer.dto.RegistrationDto;
import com.tracer.todo_tracer.entity.UserEntity;
import com.tracer.todo_tracer.exception.ExceptionConvertPriority;
import com.tracer.todo_tracer.exception.ExistUserWithByLoginException;
import com.tracer.todo_tracer.model.UserModel;
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

    public void registration(RegistrationDto regDto) throws ExistUserWithByLoginException {

        boolean isExist = userRepository
                .findByLogin(regDto.getLogin())
                .isPresent();

        if(isExist){
            throw new ExistUserWithByLoginException();
        }

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
            System.out.println(userEntity.getEmail());
            authDto.setEmail(userEntity.getEmail());
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

    public UserEntity updateUserData(String login, RegistrationDto user){

        UserEntity entity = userRepository
                .findByLogin(login)
                .orElseThrow();

        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        entity.setLogin(user.getLogin());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        entity.setToken(jwtService.generateToken(entity));

        userRepository.save(entity);

        return entity;
    }

    public void logout(String login){
        UserEntity entity = userRepository
                .findByLogin(login)
                .orElseThrow();

        entity.setToken("");

        userRepository.save(entity);
    }

    public UserModel getUserByLogin(String login){
        return userRepository
                .findByLogin(login)
                .map(UserModel::new)
                .orElseThrow();
    }


    public boolean isExpired(String login) {

        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow();
        return jwtService.isTokenExpired(userEntity.getToken());
    }

    public boolean isEquals(String login, PasswordDto passwordDto){
        String password = userRepository
                .findByLogin(login)
                .orElseThrow()
                .getPassword();

        return passwordEncoder.matches(passwordDto.getPassword(), password);
    }
}
