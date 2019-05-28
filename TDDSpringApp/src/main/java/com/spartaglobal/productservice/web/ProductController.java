package com.spartaglobal.productservice.web;

import com.spartaglobal.productservice.model.Product;
import com.spartaglobal.productservice.service.ProductService;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Returns the product with the specified ID.
     *
     * @param id    The ID of the product to retrieve.
     * @return      The product with the specified ID.
     */
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Integer id){
        return productService.findById(id)
                .map(product -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(product.getVersion()))
                                .location(new URI("/product/" + product.getId()))
                                .body(product);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Return all products in the database.
     *
     * @return  All products in the database.
     */
    @GetMapping("/products")
    public Iterable<Product> getProducts() {
        return ProductService.findAll();
    }
}
