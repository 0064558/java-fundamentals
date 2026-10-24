package com.rodrigs.inventory_control.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// A anotação @Configuration indica que a classe é uma classe de configuração do Spring,
// permitindo que você defina beans e configurações específicas para a aplicação.
@Configuration
public class SecurityConfig {

    // O método passwordEncoder é anotado com @Bean, o que significa que ele será registrado como um bean no contexto do Spring.
    // Um bean é um objeto gerenciado pelo Spring, e a anotação @Bean permite que você defina explicitamente como o bean deve ser criado.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // O método retorna uma instância de BCryptPasswordEncoder, que é uma implementação do PasswordEncoder fornecida pelo Spring Security.
        // O BCryptPasswordEncoder é usado para criptografar senhas de forma segura
        return new BCryptPasswordEncoder();
    }
}
