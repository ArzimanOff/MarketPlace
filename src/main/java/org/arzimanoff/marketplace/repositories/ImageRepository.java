package org.arzimanoff.marketplace.repositories;

import org.arzimanoff.marketplace.models.Product;
import org.arzimanoff.marketplace.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ProductImage, Long> {
}
