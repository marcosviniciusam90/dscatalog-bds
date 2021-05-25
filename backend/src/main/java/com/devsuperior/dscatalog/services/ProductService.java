package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.mapper.ProductMapper;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseIntegrityException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private static final ProductMapper PRODUCT_MAPPER = ProductMapper.INSTANCE;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> pageList = productRepository.findAll(pageable);
        return pageList.map(PRODUCT_MAPPER::entityToDTOWithoutCategories);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ResourceNotFoundException(id));
        return PRODUCT_MAPPER.entityToDTO(product);
    }

    @Transactional
    public ProductDTO create(ProductDTO productDTO) {
        Product product = PRODUCT_MAPPER.dtoToEntity(productDTO);
        product = productRepository.save(product);
        return PRODUCT_MAPPER.entityToDTO(product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        try {
            Product previousProduct = productRepository.getOne(id);

            Product product = PRODUCT_MAPPER.dtoToEntity(productDTO);
            product.setId(previousProduct.getId());
            product = productRepository.save(product);
            return PRODUCT_MAPPER.entityToDTO(product);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseIntegrityException(id);
        }
    }
}
