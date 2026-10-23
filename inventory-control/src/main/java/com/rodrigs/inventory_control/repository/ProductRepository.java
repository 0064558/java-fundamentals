package com.rodrigs.inventory_control.repository;

import com.rodrigs.inventory_control.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
* Uma interface em Java serve para definir um contrato que outras classes podem implementar.
* Ela declara métodos que devem ser implementados pelas classes que a utilizam, mas não fornece uma implementação concreta desses métodos.
* As interfaces são usadas para definir comportamentos comuns entre diferentes classes, permitindo a criação de código mais flexível e modular.
* */

@Repository // A anotação @Repository indica que a interface ProductRepository é um repositório Spring, responsável por encapsular a lógica de acesso a dados e fornecer métodos para interagir com a entidade Product no banco de dados.
public interface ProductRepository extends JpaRepository<Product, Long> {
    // A interface ProductRepository estende JpaRepository, que é uma interface do Spring Data JPA.
    // Isso significa que ProductRepository herda métodos para realizar operações CRUD (Create, Read, Update, Delete) e consultas no banco de dados para a entidade Product.
}
