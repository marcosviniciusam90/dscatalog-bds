package com.devsuperior.dscatalog.dto;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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
    private List<CategoryDTO> categories = new ArrayList<>();
}
