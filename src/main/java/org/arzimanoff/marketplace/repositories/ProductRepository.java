package org.arzimanoff.marketplace.repositories;

import org.arzimanoff.marketplace.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByTitle(String title);
    List<Product> searchProductsByTitleLikeIgnoreCase(String title);
}
