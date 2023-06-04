package com.deyvidsantos.productapi.controllers;

import java.util.List;

import com.deyvidsantos.shoppingclient.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deyvidsantos.productapi.exception.ProductNotFoundException;
import com.deyvidsantos.productapi.services.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO userDTO) {
        return productService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    public ProductDTO delete(@PathVariable Long id) throws ProductNotFoundException {
        return productService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPage(Pageable pageable) {
        return productService.getAllPage(pageable);
    }

    @PostMapping("/{id}")
    public ProductDTO editProduct(@PathVariable Long id,
            @RequestBody ProductDTO productDTO) {
        return productService.editProduct(id, productDTO);
    }

}
