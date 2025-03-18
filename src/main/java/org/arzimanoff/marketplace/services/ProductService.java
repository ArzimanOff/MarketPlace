package org.arzimanoff.marketplace.services;

import org.arzimanoff.marketplace.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private long idCounter = 1L;

    {
        products.add(
                new Product(
                        idCounter++,
                        "iPhone 16",
                        "128 гб, б/у",
                        55000,
                        "Москва",
                        "Ахмед"
                )
        );

        products.add(
                new Product(
                        idCounter++,
                        "PlayStation 5",
                        "Новый",
                        60000,
                        "Питер",
                        "Arzimanoff"
                )
        );
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void saveProduct(Product product){
        product.setId(idCounter++);
        products.add(product);
    }

    public Product findProductById(long id){
        for (Product product : products){
            if (product.getId() == id) return product;
        }
        return null;
    }

    public void delete(long id){
        products.removeIf(product -> product.getId() == id);
    }

}
