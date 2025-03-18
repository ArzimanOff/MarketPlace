package org.arzimanoff.marketplace.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arzimanoff.marketplace.models.Product;
import org.arzimanoff.marketplace.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
//    private List<Product> products = new ArrayList<>();
//
//    {
//        products.add(
//                new Product(
//                        idCounter++,
//                        "iPhone 16",
//                        "128 гб, б/у",
//                        55000,
//                        "Москва",
//                        "Ахмед"
//                )
//        );
//
//        products.add(
//                new Product(
//                        idCounter++,
//                        "PlayStation 5",
//                        "Новый",
//                        60000,
//                        "Питер",
//                        "Arzimanoff"
//                )
//        );
//    }

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(String title) {
        if (title == null || title.isEmpty()){
            return productRepository.findAll();
        }
        return productRepository.searchProductsByTitleLikeIgnoreCase("%" + title.trim() + "%");
    }

    public void saveProduct(Product product){
        productRepository.save(product);
        log.info("В базу сохранена запись {} ", product);
    }

    public Optional<Product> findProductById(long id){
        return productRepository.findById(id);
    }

    public void delete(long id){
        productRepository.deleteById(id);
    }

}
