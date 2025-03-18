package org.arzimanoff.marketplace.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arzimanoff.marketplace.models.Product;
import org.arzimanoff.marketplace.models.ProductImage;
import org.arzimanoff.marketplace.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        if (title == null || title.isEmpty()) {
            return productRepository.findAll();
        }
        return productRepository.searchProductsByTitleLikeIgnoreCase("%" + title.trim() + "%");
    }

    public void saveProduct(Product product, MultipartFile... files) throws IOException {
        boolean previewImageIsSet = false;
        for (MultipartFile file : files) {
            ProductImage img;
            if (file.getSize() != 0){
                img = toProductImageEntity(file);
                if (!previewImageIsSet){
                    img.setPreviewImage(true);
                    previewImageIsSet = true;
                }
                product.addImageToProduct(img);
            }
        }

        Product productFromDB = productRepository.save(product);
        productFromDB.setPreviewImageId(
                productFromDB.getImageList().get(0).getId()
        );
        productRepository.save(productFromDB);

        log.info("В базу сохранена запись {} ", product);
    }

    private ProductImage toProductImageEntity(MultipartFile file) throws IOException {
        ProductImage img = new ProductImage();

        img.setName(file.getName());
        img.setOriginalFilename(file.getOriginalFilename());
        img.setSize(file.getSize());
        img.setContentType(file.getContentType());
        img.setBytes(file.getBytes());

        return img;
    }

    public Optional<Product> findProductById(long id) {
        return productRepository.findById(id);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

}
