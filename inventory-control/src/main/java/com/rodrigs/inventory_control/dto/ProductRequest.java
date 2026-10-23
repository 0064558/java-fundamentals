package com.rodrigs.inventory_control.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/*
* Um DTO (Data Transfer Object) é um padrão de projeto usado para transferir dados entre diferentes camadas de uma aplicação, como entre a camada de apresentação e a camada de serviço.
* Ele é usado para encapsular os dados que precisam ser enviados ou recebidos, geralmente em operações de entrada e saída, como requisições HTTP.
* O uso de DTOs ajuda a separar a lógica de negócios da lógica de apresentação, promovendo uma arquitetura mais limpa e facilitando a manutenção do código.
* */

public record ProductRequest(

        @NotBlank
        String name,

        @NotNull
        @Positive
        BigDecimal price,

        @NotNull
        @PositiveOrZero
        Integer quantity
) {
}
