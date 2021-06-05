package com.devsuperior.dscatalog.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;

    @Setter(AccessLevel.NONE)
    private Set<CategoryDTO> categories = new HashSet<>();
}
