package com.rodrigs.inventory_control.service;

/*
* A camada de Service em uma aplicação Spring Boot é responsável por conter a lógica de negócios da aplicação.
* Ela atua como uma ponte entre os controladores (Controllers) e os repositórios (Repositories), processando dados, aplicando regras de negócio e coordenando operações antes de interagir com o banco de dados ou retornar respostas para o cliente.
* */

import com.rodrigs.inventory_control.dto.ProductRequest;
import com.rodrigs.inventory_control.dto.ProductResponse;
import com.rodrigs.inventory_control.entity.Product;
import com.rodrigs.inventory_control.exceptions.ProductNotFoundException;
import com.rodrigs.inventory_control.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// A anotação @Service indica que a classe ProductService é um componente de serviço do Spring, responsável por conter a lógica de negócios relacionada à entidade Product.
@Service
public class ProductService {
    private final ProductRepository productRepository;

    // Injeção de dependência é um padrão de design usado para fornecer as dependências necessárias a uma classe, em vez de a própria classe criar ou gerenciar essas dependências. Isso promove um código mais modular, testável e flexível.
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // A anotação @Transactional indica que o método createProduct deve ser executado dentro de uma transação. Isso significa que todas as operações realizadas dentro do método serão tratadas como uma única unidade de trabalho, garantindo consistência e integridade dos dados no banco de dados.
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        // Cria um novo objeto Product usando os dados fornecidos no ProductRequest.
        Product product = new Product(request.name(), request.price(), request.quantity());

        // Salva o objeto Product no banco de dados usando o repositório.
        Product savedProduct = productRepository.save(product);

        // Retorna um ProductResponse contendo os dados do produto salvo, incluindo o ID gerado pelo banco de dados.
        return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), savedProduct.getQuantity());
    }

    // Encontra todos os produtos no banco de dados e retorna uma lista de ProductResponse.
    @Transactional(readOnly = true)
    public List<ProductResponse> findAllProducts() {
        // Recupera todos os produtos do banco de dados usando o repositório.
        List<Product> products = productRepository.findAll();

        // Converte a lista de produtos em uma lista de ProductResponse e retorna.
        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getQuantity()))
                .toList();
    }

    // Encontra um produto pelo ID fornecido e retorna um ProductResponse. Se o produto não for encontrado, lança uma exceção ProductNotFoundException.
    @Transactional(readOnly = true)
    public ProductResponse findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    // Atualiza um produto existe pelo ID
    @Transactional
    public ProductResponse updateProduct(ProductRequest request, Long id) {
        // Encontra o produto pelo ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Atualiza os campos do produto com os dados fornecidos no ProductRequest
        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());

        // Salva o produto atualizado no banco de dados
        Product updatedProduct = productRepository.save(product);

        // Retorna um ProductResponse contendo os dados do produto atualizado
        return new ProductResponse(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getQuantity()
        );
    }

    @Transactional
    public void deleteProduct(Long id) {
        // Encontra o produto pelo ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        // Deleta o produto no banco de dados
        productRepository.delete(product);
    }
}
