package com.rodrigs.inventory_control.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

// A anotação @Table mapeia a classe Product para a tabela "product" no banco de dados.
@Table(name = "product")
// Anotação @Entity indica que a classe Product é uma entidade JPA, ou seja, ela será mapeada para uma tabela no banco de dados.
@Entity
public class Product {
    @Id // A anotação @Id indica que o atributo "id" é a chave primária da entidade Product.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // A anotação @GeneratedValue indica que o valor do atributo "id" será gerado automaticamente pelo banco de dados. IDENTITY é uma estratégia de geração de chave primária que permite que o banco de dados gere automaticamente um valor único para a chave primária quando um novo registro é inserido na tabela.
    private Long id;

    @Column(nullable = false) // A anotação @Column indica que o atributo "name" será mapeado para uma coluna na tabela "product". O parâmetro nullable = false indica que a coluna não pode ser nula, ou seja, é obrigatória.
    private String name;

    @Column(nullable = false, precision = 10, scale = 2) // A anotação @Column indica que o atributo "price" será mapeado para uma coluna na tabela "product". O parâmetro nullable = false indica que a coluna não pode ser nula, ou seja, é obrigatória. O parâmetro precision = 10 indica que o número total de dígitos permitidos é 10, e o parâmetro scale = 2 indica que o número de dígitos à direita da vírgula decimal é 2.
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    // Construtor padrão necessário para o JPA.
    public Product() {}

    // Construtor com parâmetros para facilitar a criação de objetos Product.
    public Product(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
