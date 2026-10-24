package com.rodrigs.inventory_control.service;

import com.rodrigs.inventory_control.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

/*
* Um token service é uma classe responsável por gerar tokens de autenticação para os usuários.
* Ele utiliza o JwtEncoder para criar tokens JWT (JSON Web Tokens) com base nas informações do usuário, como ID e função (role).
* O token gerado inclui informações como o assunto (subject), a data de emissão (issuedAt), a data de expiração (expiresAt) e a função do usuário.
* A duração do token é configurável através de uma propriedade externa.
*/

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;
    private final Duration expiration;

    // O construtor da classe TokenService recebe um JwtEncoder e uma string de expiração como parâmetros.
    public TokenService(
            JwtEncoder jwtEncoder,
            @Value("${security.jwt.expiration}") String expiration) {

        this.jwtEncoder = jwtEncoder;
        this.expiration = Duration.parse(expiration);
    }

    // O método generateToken é responsável por gerar um token JWT para um usuário específico.
    public String generateToken(User user) {
        // O método Instant.now() retorna a data e hora atual no formato Instant, que representa um ponto específico no tempo.
        Instant now = Instant.now();

        // O JwtClaimsSet é usado para construir o conjunto de reivindicações (claims) do token JWT.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                // A reivindicação "subject" é definida como o ID do usuário convertido para string.
                .subject(user.getId().toString())
                // A reivindicação "issuedAt" é definida como a data e hora atual (now).
                .issuedAt(now)
                // A reivindicação "expiresAt" é definida como a data e hora atual mais a duração de expiração configurada.
                .expiresAt(now.plus(expiration))
                // A reivindicação "role" é definida como o nome da função (role) do usuário.
                .claim("role", user.getRole().name())
                // O método build() é chamado para criar o objeto JwtClaimsSet com as reivindicações definidas.
                .build();

        // O JwsHeader é usado para construir o cabeçalho do token JWT, especificando o algoritmo de assinatura (HS256).
        JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        // O JwtEncoderParameters é usado para combinar o cabeçalho e as reivindicações em um único objeto, que será passado para o JwtEncoder.
        JwtEncoderParameters parameters =
                JwtEncoderParameters.from(header, claims);

        // O método encode() do JwtEncoder é chamado com os parâmetros do token, e o valor do token gerado é retornado como uma string.
        return jwtEncoder.encode(parameters).getTokenValue();
    }
}