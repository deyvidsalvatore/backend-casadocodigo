package com.deyvidsantos.shoppingclient.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    
    @NotBlank
    private String productIdentifier;
    @NotBlank
    private String nome;

    private Float preco;

    private CategoryDTO category;
}
