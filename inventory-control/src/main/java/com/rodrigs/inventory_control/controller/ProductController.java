package com.rodrigs.inventory_control.controller;

import com.rodrigs.inventory_control.dto.ProductRequest;
import com.rodrigs.inventory_control.dto.ProductResponse;
import com.rodrigs.inventory_control.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
* Um controller em Java, especialmente no contexto do Spring Boot, é uma classe responsável por lidar com as requisições HTTP recebidas pelo servidor.
* Ele atua como intermediário entre a camada de apresentação (como uma interface web ou API) e a camada de serviço ou lógica de negócios da aplicação.
* O controller processa as requisições, chama os serviços apropriados e retorna respostas ao cliente.
* */

// A anotação @RestController é usada para definir uma classe como um controlador REST no Spring Boot.
// Ela combina as funcionalidades de @Controller e @ResponseBody, permitindo que os métodos da classe retornem diretamente objetos que serão convertidos em JSON ou XML para a resposta HTTP.
@RestController()
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // A anotação @PostMapping é usada para mapear requisições HTTP POST para um método específico do controlador.
    // Ela indica que o método anotado deve ser chamado quando uma requisição POST for feita para o endpoint especificado (neste caso, "/products").
    @PostMapping
    // A anotação @ResponseStatus é usada para definir o código de status HTTP que deve ser retornado na resposta da requisição.
    // Neste caso, HttpStatus.CREATED indica que a requisição foi bem-sucedida e resultou na criação de um novo recurso (produto).
    @ResponseStatus(HttpStatus.CREATED)
    // A anotação @Valid é usada para habilitar a validação do DTO.
    // @RequestBody faz o Spring ler o JSON e converte-lo para o objeto ProductRequest.
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    // A anotação @GetMapping é usada para mapear requisições HTTP GET para um método específico do controlador.
    @GetMapping
    // O método findAllProducts é responsável por lidar com requisições GET para o endpoint "/products" e retornar uma lista de todos os produtos disponíveis.
    public List<ProductResponse> findAllProducts() {
        return productService.findAllProducts();
    }

    // A anotação @GetMapping("/{id}") é usada para mapear requisições HTTP GET para um método específico do controlador,
    //  onde "{id}" é um parâmetro de caminho que representa o ID do produto a ser buscado.
    @GetMapping("/{id}")
    // A anotação @PathVariable é usada para vincular o valor do parâmetro de caminho "{id}" ao parâmetro do método findProductById.
    public ProductResponse findProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    // A anotação @PutMapping("/{id}") é usada para mapear requisições HTTP PUT para um método específico do controlador,
    //  onde "{id}" é um parâmetro de caminho que representa o ID do produto
    @PutMapping("/{id}")
    // O método updateProduct é responsável por lidar com requisições PUT para o endpoint "/products/{id}" e atualizar os dados de um produto existente com base no ID fornecido.
    public ProductResponse updateProduct(
            @Valid @RequestBody
            ProductRequest request,
            @PathVariable
            Long id
    ) {
        return productService.updateProduct(request, id);
    }
}
