package com.deyvidsantos.productapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.deyvidsantos.shoppingclient.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deyvidsantos.productapi.converter.DTOConverter;
import com.deyvidsantos.productapi.exception.CategoryNotFoundException;
import com.deyvidsantos.productapi.exception.ProductNotFoundException;
import com.deyvidsantos.productapi.models.Product;
import com.deyvidsantos.productapi.repositories.CategoryRepository;
import com.deyvidsantos.productapi.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products.stream().map(DTOConverter::convert).collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return DTOConverter.convert(product);
        }
        throw new ProductNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO) {
        boolean existsCategory = categoryRepository.existsById(productDTO.getCategory().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }
        Product product = productRepository.save(Product.convert(productDTO));
        return DTOConverter.convert(product);
    }

    public ProductDTO delete(long id) throws ProductNotFoundException {
        Optional<Product> Product = productRepository.findById(id);
        Product.ifPresent(productRepository::delete);
        throw new ProductNotFoundException();
    }

    public ProductDTO editProduct(long id, ProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.getNome() != null) {
            product.setNome(dto.getNome());
        }
        product.setPreco(dto.getPreco());
        return DTOConverter.convert(productRepository.save(product));
    }

    public Page<ProductDTO> getAllPage(Pageable page) {
        Page<Product> users = productRepository.findAll(page);
        return users
                .map(DTOConverter::convert);
    }

}