package com.rodrigs.inventory_control.config;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// A anotação @Configuration indica que a classe é uma classe de configuração do Spring,
// permitindo que você defina beans e configurações específicas para a aplicação.
@Configuration
public class SecurityConfig {

    // O método passwordEncoder é anotado com @Bean, o que significa que ele será registrado como um bean no contexto do Spring.
    // Um bean é um objeto gerenciado pelo Spring, e a anotação @Bean permite que você defina explicitamente como o bean deve ser criado.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // O método retorna uma instância de BCryptPasswordEncoder, que é uma implementação do PasswordEncoder fornecida pelo Spring Security.
        // O BCryptPasswordEncoder é usado para gerar hashes de senhas de forma segura
        return new BCryptPasswordEncoder();
    }

    // O método jwtSecretKey é anotado com @Bean, indicando que ele será registrado como um bean no contexto do Spring.
    // Ele recebe um valor de configuração chamado "security.jwt.secret" usando a anotação @Value, que permite injetar valores de propriedades externas no código.
    @Bean
    public SecretKey jwtSecretKey(@Value("${security.jwt.secret}") String secret) {

        // O método decodifica a string secreta em bytes usando Base64.
        byte[] keyBytes = Base64.getDecoder().decode(secret);

        // Em seguida, ele cria uma instância de SecretKeySpec usando os bytes decodificados e o algoritmo "HmacSHA256".
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // O método jwtEncoder é anotado com @Bean, indicando que ele será registrado como um bean no contexto do Spring.
    // Ele recebe um SecretKey como parâmetro, que é injetado pelo Spring a partir do bean jwtSecretKey definido anteriormente.
    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
        // O método retorna uma instância de NimbusJwtEncoder, que é uma implementação do JwtEncoder fornecida pelo Spring Security.
        // O NimbusJwtEncoder é configurado com a chave secreta (jwtSecretKey) e o algoritmo de assinatura HMAC-SHA256 (MacAlgorithm.HS256).
        return NimbusJwtEncoder
                .withSecretKey(jwtSecretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }

    // O método jwtDecoder é anotado com @Bean, indicando que ele será registrado como um bean no contexto do Spring.
    // Ele recebe um SecretKey como parâmetro, que é injetado pelo Spring a partir do bean jwtSecretKey definido anteriormente.
    @Bean
    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey) {
        // O método retorna uma instância de NimbusJwtDecoder, que é uma implementação do JwtDecoder fornecida pelo Spring Security.
        // O NimbusJwtDecoder é configurado com a chave secreta (jwtSecretKey) e o algoritmo de assinatura HMAC-SHA256 (MacAlgorithm.HS256).
        return NimbusJwtDecoder
                .withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    // O método securityFilterChain é anotado com @Bean, indicando que ele será registrado como um bean no contexto do Spring.
    // Ele recebe dois parâmetros: HttpSecurity e JwtAuthenticationConverter, que são injetados pelo Spring.
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationConverter jwtAuthenticationConverter
    ) throws Exception {

        // O método retorna uma configuração de segurança para a aplicação, utilizando o objeto HttpSecurity fornecido pelo Spring Security.
        return http
                .csrf(csrf -> csrf.disable())

                // Configura a política de criação de sessão para STATELESS, o que significa que a aplicação não manterá sessões de usuário no servidor.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configura as regras de autorização para diferentes endpoints da aplicação.
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                "/auth/register",
                                "/auth/login"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/products",
                                "/products/**"
                        ).hasAnyRole("USER", "ADMIN")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/products"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/products/*"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/products/*"
                        ).hasRole("ADMIN")

                        .anyRequest().denyAll()
                )

                // Configura a autenticação baseada em tokens JWT (JSON Web Tokens) para a aplicação.
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(
                                        jwtAuthenticationConverter)
                        )
                )
                .build();
    }

    // O método jwtAuthenticationConverter é anotado com @Bean, indicando que ele será registrado como um bean no contexto do Spring.
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // O método cria uma instância de JwtGrantedAuthoritiesConverter, que é responsável por converter as reivindicações (claims) do token JWT em autoridades (roles) do Spring Security.
        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        // Configura o nome da reivindicação (claim) que contém as funções (roles) do usuário no token JWT.
        authoritiesConverter.setAuthoritiesClaimName("role");
        authoritiesConverter.setAuthorityPrefix("ROLE_");

        // O método cria uma instância de JwtAuthenticationConverter, que é responsável por converter o token JWT em um objeto de autenticação do Spring Security.
        JwtAuthenticationConverter authenticationConverter =
                new JwtAuthenticationConverter();

        // Configura o JwtAuthenticationConverter para usar o JwtGrantedAuthoritiesConverter criado anteriormente, permitindo que as funções (roles) do usuário sejam extraídas corretamente do token JWT.
        authenticationConverter.setJwtGrantedAuthoritiesConverter(
                authoritiesConverter);

        return authenticationConverter;
    }
}
