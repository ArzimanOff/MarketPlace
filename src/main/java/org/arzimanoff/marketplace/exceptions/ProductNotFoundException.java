package org.arzimanoff.marketplace.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Нет продукта с id = " + id);
    }
}
