package com.rodrigo.demo;

import java.math.BigDecimal;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        List<Product> products = List.of(
                new Product("Caderno", new BigDecimal("20.00")),
                new Product("Caneta", new BigDecimal("5.00")),
                new Product("Mochila", new BigDecimal("120.00"))
        );

        ProductService productService = new ProductService();

        System.out.println("Produtos: " + products);
        System.out.println("Total: R$ " + productService.calculateTotalValue(products));
        System.out.println("Produto mais barato: " + productService.findCheaper(products));
        System.out.println("Produtos com limite de R$ 20.00: "
                + productService.filterProduct(products, new BigDecimal("20.00"))
        );
    }
}
