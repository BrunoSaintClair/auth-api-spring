package com.example.Auth_Api.config;

import com.example.Auth_Api.security.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable()) // desabilita proteção csrf
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS
                        )) // define que a aplicação não mantém sessões (stateless)
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(HttpMethod.GET, "/msg/admin").hasRole("ADMIN") // só permite acessar quem tem o role admin
                                .requestMatchers(HttpMethod.GET, "/msg/logged-in").authenticated() // só pode acessar quem estiver autenticado
                                .anyRequest().permitAll() // qualquer outra requisição pode ser acessada por todos
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // adiciona o securityFilter antes do UsernamePasswordAuthenticationFilter.
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // fornece um AuthenticationManager para a aplicação.
        // esse gerenciador é responsável por autenticar os usuários no login.
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // define o algoritmo de hash das senhas.
        return new BCryptPasswordEncoder();
    }
}
