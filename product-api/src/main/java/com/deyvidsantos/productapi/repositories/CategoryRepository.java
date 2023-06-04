package com.deyvidsantos.productapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deyvidsantos.productapi.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
