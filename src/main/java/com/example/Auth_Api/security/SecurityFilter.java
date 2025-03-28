package com.example.Auth_Api.security;

import com.example.Auth_Api.entity.User;
import com.example.Auth_Api.repository.UserRepository;
import com.example.Auth_Api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var email = tokenService.validateToken(token);
            if (email != null) {
                User user = userRepository.findByEmail(email);
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); // representa um usuário autenticado.
                    SecurityContextHolder.getContext().setAuthentication(authentication); // define o usuário autenticado no contexto de segurança do Spring.
                }
            }
        }

        filterChain.doFilter(request, response);
        // continua o processamento da requisição depois de tratar a autenticação, passa para o próximo filtro, ou caso não tenha, vai pro controller.
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
