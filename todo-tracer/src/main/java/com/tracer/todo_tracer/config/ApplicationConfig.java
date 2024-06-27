package com.tracer.todo_tracer.config;

import com.tracer.todo_tracer.comparator.TodoComparatorDate;
import com.tracer.todo_tracer.comparator.TodoComparatorNames;
import com.tracer.todo_tracer.comparator.TodoComparatorPriority;
import com.tracer.todo_tracer.model.TodoModel;
import com.tracer.todo_tracer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Comparator;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository
                .findByEmail(username).orElseThrow();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Map<String, Comparator<TodoModel>> comparators(){
        return Map.of(
                "priority", new TodoComparatorPriority(),
                "name", new TodoComparatorNames(),
                "date", new TodoComparatorDate()
        );
    }
}
