package org.arzimanoff.marketplace.exceptions;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(Long id) {
        super("У этого продукта нет постера с id = " + id);
    }
}
