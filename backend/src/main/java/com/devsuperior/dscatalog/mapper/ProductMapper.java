package com.devsuperior.dscatalog.mapper;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product dtoToEntity(ProductDTO productDTO);

    ProductDTO entityToDTO(Product entity);

    @Mapping(target = "categories", ignore = true)
    ProductDTO entityToDTOWithoutCategories(Product entity);
}
