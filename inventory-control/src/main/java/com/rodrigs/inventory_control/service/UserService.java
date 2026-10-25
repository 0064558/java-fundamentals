package com.rodrigs.inventory_control.service;

import com.rodrigs.inventory_control.config.SecurityConfig;
import com.rodrigs.inventory_control.dto.AuthResponse;
import com.rodrigs.inventory_control.dto.UserRequest;
import com.rodrigs.inventory_control.dto.UserResponse;
import com.rodrigs.inventory_control.entity.User;
import com.rodrigs.inventory_control.enums.Role;
import com.rodrigs.inventory_control.exceptions.InvalidCredentialsException;
import com.rodrigs.inventory_control.exceptions.UserAlreadyExistsException;
import com.rodrigs.inventory_control.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Transactional
    public AuthResponse registerUser(UserRequest request) {
        // Verificar se o e-mail já existe
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("User already exists :(");
        }

        // Gerar o hash da senha
        String passwordHash = passwordEncoder.encode(request.password());

        // Criar o user
        User user = new User(request.email(), passwordHash, Role.USER);

        // Salvar no banco pelo repository do user
       User savedUser = userRepository.save(user);

       String token = tokenService.generateToken(savedUser);

        // Retornar a entidade user para uma resposta em DTO
        return new AuthResponse(token, "Bearer");
    }

    @Transactional(readOnly = true)
    public AuthResponse loginUser(UserRequest request) {
        // Buscar usuário pelo e-mail
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException("E-mail or password invalid"));

        // Verificar as credenciais
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException("E-mail or password invalid");
        }

        // Gerar token JWT
        String token = tokenService.generateToken(user);

        // Retornar a resposta de autenticação
        return new AuthResponse(token, "Bearer");
    }
}
