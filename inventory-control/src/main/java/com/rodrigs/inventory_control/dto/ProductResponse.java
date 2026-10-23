package com.rodrigs.inventory_control.dto;

import java.math.BigDecimal;

/*
 * Um DTO (Data Transfer Object) é um padrão de projeto usado para transferir dados entre diferentes camadas de uma aplicação, como entre a camada de apresentação e a camada de serviço.
 * Ele é usado para encapsular os dados que precisam ser enviados ou recebidos, geralmente em operações de entrada e saída, como requisições HTTP.
 * O uso de DTOs ajuda a separar a lógica de negócios da lógica de apresentação, promovendo uma arquitetura mais limpa e facilitando a manutenção do código.
 * */

public record ProductResponse(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity
) {
}
