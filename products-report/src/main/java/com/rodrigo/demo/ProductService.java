package com.rodrigo.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public BigDecimal calculateTotalValue(List<Product> products) {
        if (products.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Product product : products) {
            total = total.add(product.getPrice());
        }

        return total;
    }

    public Product findCheaper(List<Product> products) {
        if (products.isEmpty()) {
            return null;
        }

        Product cheaperProduct = products.getFirst();

        for (Product product : products) {
            if (product.getPrice().compareTo(cheaperProduct.getPrice()) < 0) {
                cheaperProduct = product;
            }
        }

        return cheaperProduct;
    }

    public List<Product> filterProduct(List<Product> products, BigDecimal limitPrice) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice().compareTo(limitPrice) <= 0) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}
