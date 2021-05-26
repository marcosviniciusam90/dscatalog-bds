package com.devsuperior.dscatalog.utils;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Factory {

    public static Product createProduct() {
        return createProduct(1L);
    }

    public static Product createProduct(Long id) {
        return Product.builder()
                .id(id)
                .name("Phone")
                .description("Good Phone")
                .price(800.0)
                .imgUrl("https://img.com/img.png")
                .date(Instant.parse("2021-05-25T03:00:00Z"))
                .categories(new HashSet<>(Collections.singletonList(createCategory())))
                .build();
    }

    public static ProductDTO createProductDTO() {
        return ProductDTO.builder()
                .id(1L)
                .name("Phone")
                .description("Good Phone")
                .price(800.0)
                .imgUrl("https://img.com/img.png")
                .date(Instant.parse("2021-05-25T03:00:00Z"))
                .categories(new ArrayList<>(Collections.singletonList(createCategoryDTO())))
                .build();
    }

    public static Category createCategory() {
        return Category.builder()
                .id(1L)
                .name("Electronics")
                .build();
    }

    public static CategoryDTO createCategoryDTO() {
        return CategoryDTO.builder()
                .id(1L)
                .name("Electronics")
                .build();
    }
}
