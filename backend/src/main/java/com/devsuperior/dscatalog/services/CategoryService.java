package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.mapper.CategoryMapper;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
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
public class CategoryService {

    private static final CategoryMapper CATEGORY_MAPPER = CategoryMapper.INSTANCE;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> pageList = categoryRepository.findAll(pageable);
        return pageList.map(CATEGORY_MAPPER::entityToDTO);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        Category category = optionalCategory.orElseThrow(() -> new ResourceNotFoundException(id));
        return CATEGORY_MAPPER.entityToDTO(category);
    }

    @Transactional
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = CATEGORY_MAPPER.dtoToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CATEGORY_MAPPER.entityToDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            Category previousCategory = categoryRepository.getOne(id);

            Category category = CATEGORY_MAPPER.dtoToEntity(categoryDTO);
            category.setId(previousCategory.getId());
            category = categoryRepository.save(category);
            return CATEGORY_MAPPER.entityToDTO(category);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseIntegrityException(id);
        }
    }
}
