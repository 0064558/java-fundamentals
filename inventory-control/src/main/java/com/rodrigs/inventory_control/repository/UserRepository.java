package com.rodrigs.inventory_control.repository;

import com.rodrigs.inventory_control.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // O método findByEmail é usado para buscar um usuário pelo seu email. Ele retorna um Optional<User>, que pode conter o usuário encontrado ou estar vazio se nenhum usuário com o email fornecido existir.
    Optional<User> findByEmail(String email);
    // O método existsByEmail é usado para verificar se um usuário com o email fornecido já existe no banco de dados. Ele retorna um valor booleano: true se o usuário existir e false caso contrário.
    boolean existsByEmail(String email);
}
