package com.rodrigs.inventory_control.service;

import com.rodrigs.inventory_control.config.SecurityConfig;
import com.rodrigs.inventory_control.dto.UserRequest;
import com.rodrigs.inventory_control.dto.UserResponse;
import com.rodrigs.inventory_control.entity.User;
import com.rodrigs.inventory_control.enums.Role;
import com.rodrigs.inventory_control.exceptions.UserAlreadyExistsException;
import com.rodrigs.inventory_control.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse registerUser(UserRequest request) {
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

        // Retornar a entidade user para uma resposta em DTO
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
}
