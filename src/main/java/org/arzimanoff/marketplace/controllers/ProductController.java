package org.arzimanoff.marketplace.controllers;

import lombok.RequiredArgsConstructor;
import org.arzimanoff.marketplace.exceptions.ProductNotFoundException;
import org.arzimanoff.marketplace.models.Product;
import org.arzimanoff.marketplace.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String getProducts(
            @RequestParam(name = "title", required = false) String title,
            Model model
    ){
        model.addAttribute("products", productService.getAllProducts(title));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model){
        Optional<Product> product = productService.findProductById(id);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        model.addAttribute("product", product.get());
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/";
    }

}
